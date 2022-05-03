package com.ryfl.controller;

import com.ryfl.Mapper.ClassificationMapper;
import com.ryfl.entity.Classification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/classification")
@CrossOrigin
@RestController
public class ClassificationController {
    @Autowired
    ClassificationMapper mapper;

    @GetMapping("/get")
    public List<Classification> getClassification(){return mapper.selectList(null);}


    @PostMapping("/post")
    public Boolean saveClassification(@RequestBody Classification classification){
        return mapper.insert(classification)==1;
    }

    @PutMapping("/put")
    public Boolean updateClassification(@RequestBody Classification classification){
        return mapper.updateById(classification)==1;
    }

    @DeleteMapping("/delete")
    public Boolean deleteClassificationByName(@RequestBody HashMap<String,Object> map){
        return mapper.deleteByMap(map)==1;
    }
}
