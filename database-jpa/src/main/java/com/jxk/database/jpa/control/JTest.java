package com.jxk.database.jpa.control;

import com.jxk.database.jpa.entity.City;
import com.jxk.database.jpa.repositories.CityRepository;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

public class JTest {

    @Resource
    private CityRepository cityRepository;


    @Test
    public void test1(){
        City city = new City();
        city.setId(1);
    }


}
