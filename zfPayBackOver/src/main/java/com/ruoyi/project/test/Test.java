package com.ruoyi.project.test;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        BigDecimal aa = new BigDecimal("0.585");
        BigDecimal bb = new BigDecimal("-0.02");
        System.out.println(aa.add(bb).doubleValue());
    }
}
