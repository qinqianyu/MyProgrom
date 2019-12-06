package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class KafkaToFile {
    private static final Logger logger = LoggerFactory.getLogger(KafkaToFile.class);
    private static long currentTime;
    private static final String outfileName = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体.txt";

    public static Properties createProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.20.134:6667,192.168.20.135:6667,192.168.20.137:6667");
        properties.put("group.id", "ddcdcd");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "60000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    public static void main(String[] args)  {
        Properties properties = createProperties();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("MarketPlayerIncrease"));
        long lastTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(outfileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (currentTime - lastTime < 1000) {
            System.out.println("jinru");
            ConsumerRecords<String, String> records = consumer.poll(100);
            System.out.println(records.isEmpty());
            for (ConsumerRecord<String, String> record : records) {
                logger.info("partition = {}, offset = {}, key = {}, value = {}", record.partition(), record.offset(), record.key(), record.value());
                System.out.println(record.value());
                try {
                    assert writer != null;
                    writer.write(record.value());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (records.isEmpty()) {
                System.out.println("kong");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentTime=System.currentTimeMillis();
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
