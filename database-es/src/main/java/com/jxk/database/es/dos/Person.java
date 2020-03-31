package com.jxk.database.es.dos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-31 15:26
 **/
@Data
@Builder
@Document(indexName = "ct3")
@Mapping(mappingPath = "/esConfig/mapping.json")
@Setting(settingPath = "/esConfig/setting.json")
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private Long id;

    private String name;

    private Long age;

}
