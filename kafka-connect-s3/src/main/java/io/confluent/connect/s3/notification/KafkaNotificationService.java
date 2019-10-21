/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.connect.s3.notification;

import io.confluent.connect.s3.S3SinkConnectorConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kafka notification service to push a notification message into topic after a file is
 * successfully uploaded to S3.
 */
public class KafkaNotificationService implements NotificationService {

  private static final Logger log = LoggerFactory.getLogger(KafkaNotificationService.class);
  private String notificationTopic;
  private KafkaProducer<String, FileUploadedMessage> producer;

  public KafkaNotificationService(S3SinkConnectorConfig config) {
    this(createProducer(config), config.getNotificationKafkaTopic());
  }

  // visible for testing
  KafkaNotificationService(KafkaProducer<String, FileUploadedMessage> producer,
                           String notificationTopic) {
    this.producer = producer;
    this.notificationTopic = notificationTopic;
  }

  private static KafkaProducer<String, FileUploadedMessage> createProducer(
      S3SinkConnectorConfig config) {
    
    Properties properties = getProducerProperties(config);
    return new KafkaProducer<>(properties);
  }

  // visible for testing
  static Properties getProducerProperties(S3SinkConnectorConfig config) {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getNotificationKafkaBroker());
    props.put(ProducerConfig.CLIENT_ID_CONFIG, config.getName());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
        config.getNotificationKafkaSchemaRegistry());

    if (config.getNotificationKafkaSaslEnabled()) {
      props.put("security.protocol", "SASL_SSL");
      props.put("sasl.mechanism", "PLAIN");
      props.put("sasl.jaas.config", getSaslJaasConfig(config));
    }

    return props;
  }

  private static String getSaslJaasConfig(S3SinkConnectorConfig config) {
    return String.format(
        "org.apache.kafka.common.security.plain.PlainLoginModule required "
            + "username=\"%s\" password=\"%s\";",
        config.getNotificationKafkaSaslUser(),
        config.getNotificationKafkaSaslPassword());
  }

  @Override
  public void send(FileUploadedMessage msg) {
    String keyId = UUID.randomUUID().toString();
    producer.send(new ProducerRecord<>(notificationTopic, keyId, msg));
  }

  @Override
  public void close() {
    log.info("Closing Notification Service");
    if (producer != null) {
      producer.close();
    }
  }
}
