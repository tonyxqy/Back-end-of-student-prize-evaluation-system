package com.rewardsystem.stuinfo.controller;

import com.rewardsystem.stuinfo.entity.StuInfo;
import com.rewardsystem.stuinfo.entity.User;
import com.rewardsystem.stuinfo.mapper.StuInfoMapper;
import com.rewardsystem.stuinfo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/stuInfo")
public class StuInfoController {
    @Autowired
    StuInfoMapper stuInfoMapper;
    @Autowired
    UserMapper userMapper;

//    //注册新用户
//    @PostMapping("/register")
//    public Boolean registerStuInfo(@RequestBody StuInfo stuInfo) {
//        //向数据库中添加新用户
//        int status = stuInfoMapper.insert(stuInfo);
//        return status == 1;
//    }

    //更新用户信息
//    @PutMapping("/update")
//    public Boolean update(@RequestBody StuInfo stuInfo) {
//        //更新数据库中指定用户信息
//        int status = stuInfoMapper.updateById(stuInfo);
//        return status == 1;
//    }

    //返回用户权限
    @PostMapping("authority")
    public String authority(@RequestBody HashMap<String, Object> map) {
        List<User> userList = userMapper.selectByMap(map);
        if (userList.isEmpty()) {
            return "Username Not Found";
        }
        return userList.get(0).getAuthority();
    }

    //得到指定用户信息
    @PostMapping("/get")
    public List<StuInfo> get(@RequestBody HashMap<String, Object> map) {
        List<User> userList = userMapper.selectByMap(map);
        if (Objects.equals(userList.get(0).getAuthority(), "admin")) {
            return stuInfoMapper.selectList(null);
        }
        return null;
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

    //修改用户权限
    @PutMapping("/change")
    public Boolean change(@RequestBody HashMap<String, Object> req) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", req.get("username"));
        List<User> userList = userMapper.selectByMap(map);
        if (userList.isEmpty()) {
            return false;
        } else {
            userList.get(0).setAuthority((String) req.get("authority"));
            userMapper.updateById(userList.get(0));
            return true;
        }
    }
}
