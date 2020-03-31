package com.jxk.database.es.config;

import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.time.Duration;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-31 09:50
 **/
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.jxk.database.es.repository")
public class EsConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        HttpHeaders defaultHeaders = new HttpHeaders();
        defaultHeaders.setBasicAuth("elastic", "123456");

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("192.168.20.57:9200")
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                //.useSsl()
                .withDefaultHeaders(defaultHeaders)
                .withBasicAuth("elastic", "123456")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

   /* @Bean
    @Override
    public EntityMapper entityMapper() {

        ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(
                elasticsearchMappingContext(), new DefaultConversionService()
        );
        entityMapper.setConversions(elasticsearchCustomConversions());

        return entityMapper;
    }*/

}
