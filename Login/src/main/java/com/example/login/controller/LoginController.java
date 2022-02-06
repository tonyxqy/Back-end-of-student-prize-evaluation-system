package com.example.login.controller;

import com.example.login.entity.User;
import com.example.login.mapper.UserMapper;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    UserMapper mapper;

    @PostMapping
    public String loginCheck(@RequestBody User user) {
        List<User> users = mapper.selectList(null);
        for(User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return u.getAuthority();
            }
        }
        return "access deny";
    }
}
