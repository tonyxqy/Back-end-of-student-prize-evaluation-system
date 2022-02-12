package com.rewardsystem.stuinfo.controller;

import com.rewardsystem.stuinfo.entity.StuInfo;
import com.rewardsystem.stuinfo.mapper.StuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/stuInfo")
public class StuInfoController {
    @Autowired
    StuInfoMapper stuInfoMapper;

    //注册新用户
    @PostMapping("/register")
    public Boolean registerStuInfo(@RequestBody StuInfo stuInfo) {
        //向数据库中添加新用户
        int status = stuInfoMapper.insert(stuInfo);
        return status == 1;
    }

    //更新用户信息
    @PutMapping("/update")
    public Boolean update(@RequestBody StuInfo stuInfo) {
        //更新数据库中指定用户信息
        int status = stuInfoMapper.updateById(stuInfo);
        return status == 1;
    }

    //得到指定用户信息
    @PostMapping("/get")
    public StuInfo get(@RequestBody HashMap<String, String> id) {
        //获取数据库中指定id信息
        return stuInfoMapper.selectById(id.get("id"));
    }

    //检查用户是否已经存在
    @PostMapping("/check")
    public Boolean check(@RequestBody HashMap<String, String> id) {
        List<StuInfo> stuInfos = stuInfoMapper.selectList(null);
        boolean flag = false;
        for (StuInfo stuInfo : stuInfos) {
            if (Objects.equals(stuInfo.getId(), id.get("id"))) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
