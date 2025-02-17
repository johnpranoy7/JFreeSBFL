package com.johnp.sbfl.bean;

import lombok.Data;

@Data
public class MethodInfo {
    private String name;
    private int methodPasses;
    private int methodFailures;

    private double suspiciousnessTarantula;
    private double suspiciousnessSbi;
    private double suspiciousnessJaccard;
    private double suspiciousnessOchiai;

    public MethodInfo(String name) {
        this.name = name;
        methodPasses = 0;
        methodFailures = 0;
    }
}
