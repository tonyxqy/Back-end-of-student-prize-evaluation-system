package com.xkjs.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("reference")
public class Reference {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String hdmc;
    private String jx;
    private String rdjb;
    private Integer fz;
}
