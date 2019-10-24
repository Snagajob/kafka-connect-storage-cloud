package io.confluent.connect.s3.notification;

import io.confluent.connect.s3.S3SinkConnectorConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

public class KafkaNotificationServiceTest {

  @Mock
  private KafkaProducer<String, FileUploadedMessage> producer;

  private KafkaNotificationService notificationService;
  private String topic = "notification-topic";

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
    notificationService = new KafkaNotificationService(producer, topic);
  }

  @Test
  public void testSend() {
    ArgumentCaptor<ProducerRecord<String, FileUploadedMessage>> recordCaptor =
        ArgumentCaptor.forClass(ProducerRecord.class);

    FileUploadedMessage message = new FileUploadedMessage();
    notificationService.send(message);

    verify(producer).send(recordCaptor.capture());

    ProducerRecord<String, FileUploadedMessage> record = recordCaptor.getValue();
    assertNotNull(record);
    assertEquals(topic, record.topic());
    assertNotNull(record.key());
    assertSame(message, record.value());
  }

  @Test
  public void testClose() {
    notificationService.close();
    verify(producer).close();
  }

  @Test
  public void testGetProducerPropertiesWithSaslDisabled() {
    S3SinkConnectorConfig config = new S3SinkConnectorConfig(getConnectorProperties(false));
    Properties properties = KafkaNotificationService.getProducerProperties(config);

    assertNotNull(properties);
    assertEquals(5, properties.size());
    assertEquals("localhost:9092", properties.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
    assertEquals("test-connector", properties.get(ProducerConfig.CLIENT_ID_CONFIG));
    assertEquals(StringSerializer.class,
        properties.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
    assertEquals(KafkaAvroSerializer.class,
        properties.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
    assertEquals("http://localhost:8081",
        properties.get(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG));
  }

  @Test
  public void testGetProducerPropertiesWithSaslEnabled() {
    S3SinkConnectorConfig config = new S3SinkConnectorConfig(getConnectorProperties(true));
    Properties properties = KafkaNotificationService.getProducerProperties(config);

    assertNotNull(properties);
    assertEquals(8, properties.size());
    assertEquals("localhost:9092", properties.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
    assertEquals("test-connector", properties.get(ProducerConfig.CLIENT_ID_CONFIG));
    assertEquals(StringSerializer.class,
        properties.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
    assertEquals(KafkaAvroSerializer.class,
        properties.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
    assertEquals("http://localhost:8081",
        properties.get(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG));
    assertEquals("SASL_SSL", properties.get("security.protocol"));
    assertEquals("PLAIN", properties.get("sasl.mechanism"));
    assertEquals("org.apache.kafka.common.security.plain.PlainLoginModule required " +
            "username=\"username\" password=\"password\";",
        properties.get("sasl.jaas.config"));
  }

  private Map<String, String> getConnectorProperties(boolean saslEnabled) {
    Map<String, String> properties = new HashMap<>();
    properties.put("name", "test-connector");
    properties.put("format.class", "io.confluent.connect.s3.format.avro.AvroFormat");
    properties.put("flush.size", "10000");
    properties.put("s3.bucket.name", "s3-bucket");
    properties.put("storage.class", "io.confluent.connect.s3.storage.S3Storage");

    properties.put("notification.kafka.broker", "localhost:9092");
    properties.put("notification.kafka.topic", "notification-topic");
    properties.put("notification.kafka.schema.registry", "http://localhost:8081");
    if (saslEnabled) {
      properties.put("notification.kafka.sasl.enabled", "true");
      properties.put("notification.kafka.sasl.username", "username");
      properties.put("notification.kafka.sasl.password", "password");
    }
    return properties;
  }
}
