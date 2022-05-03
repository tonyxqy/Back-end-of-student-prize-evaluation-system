package com.rewardsystem.stuinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rewardsystem.stuinfo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
