package com.rewardsystem.honor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("evaluation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {
    @TableId(type = IdType.AUTO)
    long id;
    String jxmc; //奖项名
    String xh; //学号
    String ry; //荣誉
}
