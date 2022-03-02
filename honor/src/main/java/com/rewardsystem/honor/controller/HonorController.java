package com.rewardsystem.honor.controller;

import com.rewardsystem.honor.entity.Evaluation;
import com.rewardsystem.honor.entity.Honor;
import com.rewardsystem.honor.entity.Receive;
import com.rewardsystem.honor.mapper.EvaluationMapper;
import com.rewardsystem.honor.mapper.HonorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/honor")
public class HonorController {
    @Autowired
    EvaluationMapper evaluationMapper;
    @Autowired
    HonorMapper honorMapper;

    @PostMapping("/insert")
    public Boolean insert(@RequestBody Receive receive) {
        System.out.println(receive);
        for (int i = 0; i < receive.getJxmc().size(); i++) {
            Honor honor = new Honor();
            Evaluation evaluation = new Evaluation();
            honor.setXh(receive.getXh());
            honor.setSqtx(receive.getXm());
            honor.setJxmc(receive.getJxmc().get(i));
            honor.setSqqk(0);
            int status = honorMapper.insert(honor);
            if (status == 0) {
                return false;
            }
            evaluation.setXh(receive.getXh());
            evaluation.setJxmc(receive.getJxmc().get(i));
            evaluation.setRy(receive.getRy());
            status = evaluationMapper.insert(evaluation);
            if (status == 0) {
                return false;
            }
        }
        return true;
    }
    @PostMapping("/select_by_reward")
    public List<Honor> selectReward(@RequestBody HashMap<String, Object> map) {
        return honorMapper.selectByMap(map);
    }
    @PostMapping("/slelect_by_xh")
    public List<Evaluation>  selectEvaluation(@RequestBody HashMap<String, Object> map) {
        return evaluationMapper.selectByMap(map);
    }
    @PutMapping("/update")
    public Boolean update(@RequestBody Honor honor) {
        int status = honorMapper.updateById(honor);
        return status == 1;
    }
}
