package com.johnp.sbfl.util;

public class SuspiciousCaluclater {

    public static double calculateTarantula(double ef, double ep, double nf, double np) {
        double failRatio = safeDiv(ef, ef + nf);
        double passRatio = safeDiv(ep, ep + np);
        return safeDiv(failRatio, failRatio + passRatio);
    }

    public static double calculateSbi(double ef, double ep) {
        return safeDiv(ef, ef + ep);
    }

    public static double calculateJaccard(double ef, double ep, double nf) {
        return safeDiv(ef, ef + nf + ep);
    }

    public static double calculateOchiai(double ef, double ep, double nf) {
        return ef / Math.sqrt((ef + nf) * (ef + ep));
    }

    private static double safeDiv(double numerator, double denominator) {
        return denominator == 0 ? 0.0 : numerator / denominator;
    }


}
