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

package io.confluent.connect.s3;

import org.apache.kafka.clients.producer.KafkaProducer;
import s3connect.FileUploadedMessage;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy;
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

  public KafkaNotificationService(S3SinkConnectorConfig s3SinkConnectorConfig) {
    this(createProducer(s3SinkConnectorConfig),
        s3SinkConnectorConfig.getNotificationKafkaTopic());
  }

  public KafkaNotificationService(KafkaProducer producer, String notificationTopic) {
    this.producer = producer;
    this.notificationTopic = notificationTopic;
  }

  private static KafkaProducer createProducer(S3SinkConnectorConfig s3SinkConnectorConfig) {
    final Properties producerProps = new Properties();
    producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        s3SinkConnectorConfig.getNotificationKafkaBroker());
    producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
    producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        KafkaAvroSerializer.class);
    producerProps.put(ProducerConfig.CLIENT_ID_CONFIG,
        s3SinkConnectorConfig.getName());
    producerProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
        s3SinkConnectorConfig.getNotificationKafkaSchemaRegistry());
    producerProps.put(KafkaAvroSerializerConfig.KEY_SUBJECT_NAME_STRATEGY,
        TopicRecordNameStrategy.class);
    producerProps.put(KafkaAvroSerializerConfig.VALUE_SUBJECT_NAME_STRATEGY,
        TopicRecordNameStrategy.class);

    if (s3SinkConnectorConfig.getNotificationKafkaSaslEnabled()) {
      producerProps.put("security.protocol", "SASL_SSL");
      producerProps.put("sasl.mechanism", "PLAIN");
      producerProps.put("sasl.jaas.config", saslJaasConfig(s3SinkConnectorConfig));
    }
    return new KafkaProducer<>(producerProps);
  }

  private static String saslJaasConfig(S3SinkConnectorConfig conf) {
    return String.format(
        "org.apache.kafka.common.security.plain.PlainLoginModule required "
            + "username=\"%s\" password=\"%s\";",
        conf.getNotificationKafkaSaslUser(),
        conf.getNotificationKafkaSaslPassword());
  }

  @Override
  public void send(FileUploadedMessage msg) {
    String keyId = UUID.randomUUID().toString();
    producer.send(new ProducerRecord<>(notificationTopic, keyId, msg));
  }

  @Override
  public void close() {
    log.info("Closing Notification Service");
    producer.close();
  }
}
