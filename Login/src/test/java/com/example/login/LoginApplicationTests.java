package com.example.login;

import com.example.login.entity.StuInfo;
import com.example.login.entity.User;
import com.example.login.mapper.StuInfoMapper;
import com.example.login.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class LoginApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StuInfoMapper stuInfoMapper;

    @Test
    void contextLoads() {
        List<User> userList = userMapper.selectList(null);
        for(User user:userList) {
            System.out.println(user);
        }
    }

    @Test
    void check() {
        User user = new User();
        user.setUsername("19120010");
        user.setPassword("abc");
        user.setAuthority("student");
        List<User> users = userMapper.selectList(null);
        HashMap<String, String> info = new HashMap<>();
        for(User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()) && u.getAuthority().equals(user.getAuthority())) {
                StuInfo stuInfo = stuInfoMapper.selectById(user.getUsername());
                if (u.getAuthority().equals("student")) {
                    info.put("id", stuInfo.getId());
                    info.put("name", stuInfo.getName());
                    info.put("grade", String.valueOf(stuInfo.getGrade()));
                } else {
                    info.put("id", stuInfo.getId());
                    info.put("name", stuInfo.getName());
                }
                System.out.println(info);
                return;
            }
        }
    }

}
