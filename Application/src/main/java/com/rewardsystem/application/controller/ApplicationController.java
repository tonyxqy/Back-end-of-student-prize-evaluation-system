package com.rewardsystem.application.controller;

import com.rewardsystem.application.entity.Application;
import com.rewardsystem.application.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    ApplicationMapper applicationMapper;

    //插入申请
    @PostMapping("/insert")
    Boolean insert(@RequestBody Application application) {
        int status = applicationMapper.insert(application);
        System.out.println("insert =>" + status);
        return status == 1;
    }

    //更新申请表
    @PutMapping("/update")
    Boolean update(@RequestBody Application application) {
        int status = applicationMapper.updateById(application);
        System.out.println("update =>" + status);
        return status == 1;
    }

    //删除申请表
    @DeleteMapping("/delete")
    Boolean delete(@RequestBody HashMap<String, Object> map) {
        int status = applicationMapper.deleteByMap(map);
        System.out.println("delete =>" + map.get("id") + status);
        return status == 1;
    }

//
//    @PostMapping("/get")
//    Application get(@RequestBody HashMap<String, Object> map) {
//        List<Application> applications = applicationMapper.selectByMap(map);
//        if (map.isEmpty()) {
//            return null;
//        } else {
//            return applications.get(0);
//        }
//    }

    //根据指定列的值返回表项
    @PostMapping("/select")
    List<Application> select(@RequestBody HashMap<String, Object> map) {
        return applicationMapper.selectByMap(map);
    }

    @PostMapping("/status")
    Boolean status(@RequestBody HashMap<String, Object> info) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", info.get("id"));
        List<Application> applicationList = applicationMapper.selectByMap(map);
        if (applicationList.isEmpty()) {
            return false;
        }
        applicationList.get(0).setDqzt((Integer) info.get("dqzt"));
        applicationMapper.updateById(applicationList.get(0));
        return true;
    }

    @GetMapping("/get")
    List<Application> get() {
        return applicationMapper.selectList(null);
    }
}
