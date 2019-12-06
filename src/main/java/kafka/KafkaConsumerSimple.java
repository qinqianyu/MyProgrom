package kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

//kafka消费者
public class KafkaConsumerSimple {
    //  Logger日志
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerSimple.class);

    // 在消费的时候生产者一定要处于运行状态，否则就会得不到数据，无法消费
    public Properties createSimpleProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "hadoop01:9092,hadoop02:9092,hadoop03:9092");
        properties.put("group.id", "mytest");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "60000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    // 自动提交偏移量
    public void simpleConsumer() {
        Properties properties = createSimpleProperties();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("flumeTest"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("partition = {}, offset = {}, key = {}, value = {}", record.partition(), record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }

    public static void main(String[] args) {
        new KafkaConsumerSimple().simpleConsumer();
    }

    public Properties createProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        properties.put("group.id", "test");
        properties.put("enable.auto.commit", "false");
        properties.put("auto.commit.interval.ms", "60000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    public void consumer() {
        Properties properties = createProperties();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("test"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("partition = {}, log4j.propertiesoffset = {}, key = {}, value = {}", record.partition(), record.offset(), record.key(), record.value());
                }
                if (!records.isEmpty()) { // 异步提交offset
                    consumer.commitAsync(new OffsetCommitCallback() {
                        @Override
                        public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                            for (Map.Entry<TopicPartition, OffsetAndMetadata> offset : offsets.entrySet()) {
                                logger.info("commit offset: partition = {}, offset = {}", offset.getKey(), offset.getValue().offset());
                            }
                        }
                    });
                }
            }
        } finally {
            consumer.close();
        }
    }
}
