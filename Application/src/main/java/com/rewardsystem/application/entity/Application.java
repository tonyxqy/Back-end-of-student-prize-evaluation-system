package com.rewardsystem.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("application")
public class Application {
//    @TableId(type = IdType.ASSIGN_ID)
    String id; //申请表id
    String xh; //学号
    String xm; //姓名
    int nj; //年级
    String hdmc; //活动名称
    Date hjrq; //获奖日期
    String jx; //奖项
    String wb; //文本
    Date tjsj; //提交时间
    int dqzt; //当前状态
}
