package com.rewardsystem.honor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("honor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Honor {
    @TableId(type = IdType.AUTO)
    long id;
    String xh; //学号
    String jxmc; //奖项名称
    String sqtx; //申请同学
    int sqqk; //申请情况
}
