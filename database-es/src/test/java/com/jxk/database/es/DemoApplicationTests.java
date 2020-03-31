package com.jxk.database.es; /**
 * @description:
 * @author: jxk
 * @create: 2020-03-31 09:47
 **/
import com.jxk.database.es.dos.Person;
import com.jxk.database.es.repository.PersonRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void contextLoads() throws IOException {
        Person person = Person.builder().id(13L).age(15L).name("张三").build();
        personRepository.save(person);
    }
}