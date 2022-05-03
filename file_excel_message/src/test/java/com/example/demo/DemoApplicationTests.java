package com.example.demo;


import com.example.demo.entity.FileStorage;
import com.example.demo.mapper.FileStorageMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests{
    @Autowired
    FileStorageMapper mapper;

    @Test
    void contextLoads(){
        FileStorage file=new FileStorage();
        file.setFilename("123");
        file.setNumber("sadasdad");
        int i=mapper.insert(file);
    }


}