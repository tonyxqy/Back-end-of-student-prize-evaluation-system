package com.xkjs.controller;

import com.xkjs.entity.Reference;
import com.xkjs.mapper.ReferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/reference")
@CrossOrigin
@RestController
public class ReferenceController {
    @Autowired
    ReferenceMapper mapper;

    @GetMapping("/get")
    public List<Reference> getReference(){
        return mapper.selectList(null);
    }

    @GetMapping("/getjx")
    public List<String> getJX(@RequestBody HashMap<String,Object> map){
        List<String> result = new LinkedList<>();
        List<Reference> temp = mapper.selectByMap(map);
        for(int i=0;i<temp.size();i++){
            result.add(temp.get(i).getJx());
        }
        return result;
    }

    @PostMapping("/post")
    public Boolean saveReference(@RequestBody Reference reference){
        return mapper.insert(reference)==1;
    }

    @PutMapping("/put")
    public Boolean updateReference(@RequestBody Reference reference){
        return mapper.updateById(reference)==1;
    }

    @DeleteMapping("/deletebyid")
    public Boolean deleteReferenceById(Integer id){
        return mapper.deleteById(id)==1;
    }

    @DeleteMapping("/deletebyname")
    public Boolean deleteReferenceByName(@RequestBody HashMap<String,Object> map){
        return mapper.deleteByMap(map)==1;
    }
}
