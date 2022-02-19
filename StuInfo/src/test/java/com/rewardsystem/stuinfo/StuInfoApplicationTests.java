package com.rewardsystem.stuinfo;

import com.rewardsystem.stuinfo.entity.StuInfo;
import com.rewardsystem.stuinfo.entity.User;
import com.rewardsystem.stuinfo.mapper.StuInfoMapper;
import com.rewardsystem.stuinfo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class StuInfoApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    StuInfoMapper stuInfoMapper;

    @Test
    void getList() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "root");
        List<User> userList = userMapper.selectByMap(map);
        if (Objects.equals(userList.get(0).getAuthority(), "admin")) {
            List<StuInfo> stuInfoList = stuInfoMapper.selectList(null);
            System.out.println(stuInfoList);
        }
    }

    @Test
    void getAuthority() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "19120000");
        List<User> userList = userMapper.selectByMap(map);
        System.out.println(userList.get(0).getAuthority());
    }

}
