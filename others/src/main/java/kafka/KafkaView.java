package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaView {
    private static Properties createProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.20.134:6667");
        properties.put("group.id", "jxk214");
        properties.put("enable.auto.commit", "false");
        properties.put("auto.offset.reset", "earliest");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    public static void main(String[] args) {
       view(Integer.MAX_VALUE);
    }

    private static void view(int counts){
        Properties properties = createProperties();
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        //kafkaConsumer.subscribe(Collections.singletonList("MarketPlayerIncrease"));
        kafkaConsumer.subscribe(Collections.singletonList("MarketPlayerResult"));
        int conut=0;
        while (conut<counts) {
            //System.out.println("-----------------");
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                System.out.println(value);
                conut++;
            }
            if (records.isEmpty()) {
                break;
            }
        }
        System.out.println(conut);
        kafkaConsumer.close();

    }




}
