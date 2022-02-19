package com.rewardsystem.application;

import com.rewardsystem.application.entity.Application;
import com.rewardsystem.application.mapper.ApplicationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    ApplicationMapper applicationMapper;

    @Test
    void contextLoads() {
        List<Application> applicationList = applicationMapper.selectList(null);
        System.out.println(applicationList);
    }

    @Test
    void insert() {
        Application application = new Application();
        application.setId("askcbfk213v");
        application.setXh("1219805456");
        application.setXm("xiaomg");
        applicationMapper.insert(application);
    }

    @Test
    void get() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", "af1va5va5d86g6de");
        List<Application> applications = applicationMapper.selectByMap(map);
        System.out.println(applications.get(0));
    }

}
