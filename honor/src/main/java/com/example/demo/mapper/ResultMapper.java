package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Evaluation;
import com.example.demo.entity.Result;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResultMapper extends BaseMapper<Result> {

}
