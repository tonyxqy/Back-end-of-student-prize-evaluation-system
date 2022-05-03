package com.ryfl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("classification")
public class Classification {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String jxmc;
    private String rymsxx;
}
