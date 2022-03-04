package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Message;
import com.example.demo.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * message交互
 * @author dyh
 */
@RestController
@CrossOrigin
public class MessageController {
    @Autowired
    MessageMapper mapper;

    @PostMapping("/sendingMessage")
    public Boolean sendingMessage(@RequestBody Message msg) {
        mapper.insert(msg);
        return true;
    }

    @PostMapping("/receiving")
    public List<Message> receivingMessage(@RequestBody Map<String,String> re){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        String recipient=re.get("recipient");
        queryWrapper
                .eq("recipient",recipient)
                .or()
                .eq("recipient","-1");
        List<Message> msg=mapper.selectList(queryWrapper);
        return msg;
    }

}
