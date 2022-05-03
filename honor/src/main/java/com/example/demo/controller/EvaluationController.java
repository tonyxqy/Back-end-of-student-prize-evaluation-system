package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Evaluation;
import com.example.demo.entity.Result;
import com.example.demo.mapper.EvaluationMapper;
import com.example.demo.mapper.ResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 *
 *
 * @author dyh
 */
@RestController
@CrossOrigin
public class EvaluationController {
    @Autowired
    EvaluationMapper mapper1;
    @Autowired
    ResultMapper mapper2;


    @PostMapping("/addApplication")
    public boolean addApplication(@RequestBody List<Evaluation> lst){

        int i=0;
        if(lst.isEmpty()) return false;
        while(i<lst.size()){
            mapper1.insert(lst.get(i));
            i++;
        }
//        QueryWrapper<Evaluation> q=new QueryWrapper<>();
//        q.eq("sign",lst.get(0).getSign());
//        List<Evaluation> l= mapper1.selectList(q);
//        if(!l.isEmpty()) return false;

        Result r=new Result();
        r.setSign(lst.get(0).getSign());
        r.setXh(lst.get(0).getXh());
        r.setXm(lst.get(0).getXm());
        r.setRy(lst.get(0).getRy());
        r.setState(0);
        mapper2.insert(r);
        return true;
    }

    @GetMapping("/getRy")
    public List<Evaluation> getRy(){
        QueryWrapper<Evaluation> q=new QueryWrapper<>();
        q.select("ry").groupBy("ry");
        List<Evaluation> lst=mapper1.selectList(q);
        return lst;
    }

    @PostMapping("/getName")
    public List<Evaluation> getName(@RequestBody HashMap<String ,String> map){
        QueryWrapper<Evaluation> q=new QueryWrapper<>();
        q.select("sign","xm").eq("ry",map.get("ry")).groupBy("sign","xm");
        List<Evaluation> lst=mapper1.selectList(q);
        return lst;
    }

    @PostMapping("/getDetail")
    public List<Evaluation> getDetail(@RequestBody HashMap<String ,String> map){
        QueryWrapper<Evaluation> q=new QueryWrapper<>();
        q.select("jx","dj").eq("xm",map.get("xm")).eq("sign",map.get("sign"));
        List<Evaluation> lst=mapper1.selectList(q);
        return lst;
    }

    @PostMapping("/setState")
    public boolean setState(@RequestBody HashMap<String ,String> map){
        QueryWrapper<Result> q=new QueryWrapper<>();
        q.eq("xm",map.get("xm")).eq("sign",map.get("sign"));
        List<Result> lst=mapper2.selectList(q);
        if(lst.isEmpty()) return false;
        lst.get(0).setState(Integer.parseInt(map.get("state")));
        mapper2.updateById(lst.get(0));
        QueryWrapper<Evaluation> e=new QueryWrapper<>();
        e.eq("sign",map.get("sign"));
        mapper1.delete(e);
        return true;
    }

    @PostMapping("/getResult")
    public List<Result> getResult(@RequestBody HashMap<String ,String> map){
        QueryWrapper<Result> q=new QueryWrapper<>();
        q.select("ry","state").eq("sign",map.get("sign")).eq("ry",map.get("ry"));
        List<Result> lst=mapper2.selectList(q);
        return lst;
    }

    @PostMapping("/getAll")
    public List<Result> getAll(@RequestBody HashMap<String ,String> map){
        QueryWrapper<Result> q=new QueryWrapper<>();
        q.eq("xh",map.get("xh"));
        List<Result> lst=mapper2.selectList(q);
        return lst;
    }

    @PostMapping("/deleteSign")
    public boolean deleteSign(@RequestBody HashMap<String ,String> map){

        QueryWrapper<Result> q=new QueryWrapper<>();
        q.eq("sign",map.get("sign"));
        QueryWrapper<Evaluation> q2=new QueryWrapper<>();
        q2.eq("sign",map.get("sign"));
        mapper1.delete(q2);
        mapper2.delete(q);
        return true;
    }

}