package com.johnp.sbfl.util;

import com.johnp.sbfl.bean.MethodInfo;
import lombok.extern.slf4j.Slf4j;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Analyzer {


    static Map<String, MethodInfo> methodMap = new HashMap<>();

    public static void analyzeFolder(File folder) {

        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    try {
                        List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
                        if (!lines.isEmpty()) {
                            String firstLine = lines.get(0);
                            String[] parts = firstLine.split(" ");
                            if (parts.length == 2) {
                                String testName = parts[0];
                                boolean testResult = Boolean.parseBoolean(parts[1]);
                                log.info("Reading File:- TestName: {} , TestResult: {}", testName, testResult);
                                constructAndUpdateMethodDetails(lines, testName, testResult);
                            }
                        }
                    } catch (IOException e) {
                        log.error("Error reading File.", e);
                    }
                }
            }

            //Analysis of Collected Data
            //Compute Suspicious Formulas
            computeSBFLForEachMethod();

            //TODO: Select Top Values and Display them

            log.info(String.valueOf(methodMap));

        } else {
            log.error("The directory is empty or does not exist.");
        }
    }

    private static void computeSBFLForEachMethod() {
        methodMap.forEach((methodName, methodInfo) -> {
            methodInfo.setSuspiciousnessTarantula(SuspiciousCaluclater.calculateTarantula(methodInfo.getExecutingTestFailure(),
                    methodInfo.getExecutingTestPassed(), methodInfo.getTotalFailures(), methodInfo.getTotalPasses()));

            methodInfo.setSuspiciousnessSbi(SuspiciousCaluclater.calculateSbi(methodInfo.getExecutingTestFailure(),
                    methodInfo.getExecutingTestPassed()));

            methodInfo.setSuspiciousnessJaccard(SuspiciousCaluclater.calculateJaccard(methodInfo.getExecutingTestFailure(),
                    methodInfo.getExecutingTestPassed(), methodInfo.getTotalFailures()));

            methodInfo.setSuspiciousnessOchiai(SuspiciousCaluclater.calculateOchiai(methodInfo.getExecutingTestFailure(),
                    methodInfo.getExecutingTestPassed(), methodInfo.getTotalFailures()));
        });
    }

    private static void constructAndUpdateMethodDetails(List<String> lines, String testName, boolean testResult) {
        lines.stream().skip(1).forEach(line -> {
            if (line.contains(":")) {
                String methodName = line.split(":[^:]+$")[0];
                System.out.println("Method Name: " + methodName);

                /*! Constructing Datastructure */
                methodMap.compute(methodName, (k, v) -> {
                    if (v == null) {
                        v = new MethodInfo(testName);
                    }
                    if (testResult) {
                        v.setExecutingTestPassed(v.getExecutingTestPassed() + 1);
                        v.setTotalPasses(v.getTotalPasses() + 1);
                    } else {
                        v.setExecutingTestFailure(v.getExecutingTestFailure() + 1);
                        v.setTotalFailures(v.getTotalFailures() + 1);
                    }
                    return v;
                });

            }
        });
    }
}
