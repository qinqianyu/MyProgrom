package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaToFile {
    private static long currentTime;
    private static final String outfileName = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体.txt";

    public static Properties createProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.20.134:6667");
        properties.put("group.id", "jxk213");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.offset.reset", "earliest");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Properties properties = createProperties();
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("MarketPlayerIncrease"));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outfileName)));
        long lastTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();
        while (currentTime - lastTime < 1000) {
            System.out.println("----");
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(485));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("22222");
                String value = record.value();
                System.out.println(value);
                writer.write(value);
                writer.newLine();
            }
            if (records.isEmpty()) {
                break;
            }
        }
        writer.close();
        kafkaConsumer.close();
    }
}
