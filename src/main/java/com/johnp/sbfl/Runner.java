package com.johnp.sbfl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {

    static Map<String, MethodInfo> methodMap = new HashMap<>();

    public static void main(String[] args) {
        File folder = new File("C:\\Users\\John Pranoy Yalla\\Desktop\\Sem2\\Software Testing\\CoverageData\\dum");
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
                                System.out.println("Reading: Test Name: " + testName + ", Test Result: " + testResult);

                                lines.stream().skip(1).forEach(line -> {
                                    if (line.contains(":")) {
                                        String methodName = line.split(":[^:]+$")[0];
                                        System.out.println("Method Name: " + methodName);

                                        /*! Constructing Datastructure */
                                        methodMap.compute(methodName, (k, v) -> {
                                            if (v == null) {
                                                v = new MethodInfo(methodName);
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            //Analysis of Collected Data
            System.out.println(methodMap);

        } else {
            System.out.println("The directory is empty or does not exist.");
        }
    }
}
