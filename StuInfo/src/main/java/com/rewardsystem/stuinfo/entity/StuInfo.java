package com.rewardsystem.stuinfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("stuinfo")
//用户信息表项
public class StuInfo {
    String id; //学号or工号
    String name; //姓名
    String sex; //性别
    String major; //专业
    int grade; //年级
    int applyAdd; //权限_添加申请
    int modifyPersonalInfo; //权限_修改个人申请信息
    int modifyOtherInfo; //权限_修改他人申请信息
    int auditCommand; //权限_审核意见
    int auditPass; //权限_通过审核
    int bulkImport; //权限_批量导入
    int readRwardInfo; //权限_查阅获奖信息
    int award; //权限_评奖评优
    int courseContestsLoading; //权限_录入学科竞赛分类表
}
