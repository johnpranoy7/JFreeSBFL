package com.johnp.sbfl;

import com.johnp.sbfl.util.Analyzer;

import java.io.File;

public class Runner {

    public static void main(String[] args) {
        File folder = new File("C:\\Users\\John Pranoy Yalla\\Desktop\\Sem2\\Software Testing\\CoverageData\\dum");
        Analyzer.analyzeFolder(folder);
    }

}
