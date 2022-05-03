package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //全参构造器
@NoArgsConstructor  //无参构造器
@Data
@TableName("tmp")
public class Temp {
    String a;
    String b;
    String c;
    String d;
}
