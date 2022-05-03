package com.rewardsystem.honor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receive {
    String xh;
    String xm;
    List<String> jxmc;
    String ry;
}
