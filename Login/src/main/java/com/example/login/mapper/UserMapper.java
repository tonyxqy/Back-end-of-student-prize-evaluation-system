package com.example.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.login.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
