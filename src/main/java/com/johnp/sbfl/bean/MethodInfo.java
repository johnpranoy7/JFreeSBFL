package com.johnp.sbfl.bean;

import lombok.Data;

@Data
public class MethodInfo {
    private String name;
    private int methodPasses;
    private int methodFailures;

    private double tarantula;
    private double sbi;
    private double jaccard;
    private double ochiai;

    private double ample;
    private double russelRao;
    private double dice;
    private double wong1;
    private double wong2;
    private double dstar2;
    private double kulczynski1;
    private double sorensenDice;
    private double gp03;
    private double gp13;

    public MethodInfo(String name) {
        this.name = name;
        methodPasses = 0;
        methodFailures = 0;
    }
}
