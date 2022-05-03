package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //全参构造器
@NoArgsConstructor  //无参构造器
@Data
@TableName("excel")
public class Excel {

    @TableId(type= IdType.AUTO)//使主键自增
    private Integer id;
    private String hdmc;
    private String  jx;
    private String rdjb;
    private Integer fz;
}