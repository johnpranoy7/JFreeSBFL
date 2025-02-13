package com.johnp.sbfl;

import lombok.Data;

@Data
public class MethodInfo {
    private String name;
    private int executingTestFailure;
    private int executingTestPassed;
    private int totalFailures;
    private int totalPasses;

    private double suspiciousnessTarantula;
    private double suspiciousnessSbi;
    private double suspiciousnessJaccard;
    private double suspiciousnessOchiai;

    public MethodInfo(String name){
        this.name = name;
        executingTestFailure = 0;
        executingTestPassed = 0;
        totalFailures = 0;
        totalPasses = 0;
    }




}
