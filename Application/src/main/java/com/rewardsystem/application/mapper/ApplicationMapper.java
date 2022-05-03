package com.rewardsystem.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rewardsystem.application.entity.Application;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationMapper extends BaseMapper<Application> {
}
