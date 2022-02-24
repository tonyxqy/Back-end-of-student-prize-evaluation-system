package com.example.login.controller;

import com.example.login.entity.StuInfo;
import com.example.login.entity.User;
import com.example.login.mapper.StuInfoMapper;
import com.example.login.mapper.UserMapper;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    StuInfoMapper stuInfoMapper;

    @PostMapping
    public HashMap<String, String> loginCheck(@RequestBody User user) {
        List<User> users = userMapper.selectList(null);
        HashMap<String, String> info = new HashMap<>();
        for(User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()) && u.getAuthority().equals(user.getAuthority())) {
                StuInfo stuInfo = stuInfoMapper.selectById(user.getUsername());
                if (u.getAuthority().equals("student")) {
                    info.put("id", stuInfo.getId());
                    info.put("name", stuInfo.getName());
                    info.put("grade", String.valueOf(stuInfo.getGrade()));
                    info.put("authority", user.getAuthority());
                } else {
                    info.put("id", stuInfo.getId());
                    info.put("name", stuInfo.getName());
                    info.put("authority", user.getAuthority());
                }
                return info;
            }
        }
        info.put("Error", "Access deny");
        return info;
    }

    @DeleteMapping("/delete")
    public Boolean delete(@RequestBody HashMap<String, Object>map) {
        int status = userMapper.deleteByMap(map);
        return status == 1;
    }
}
