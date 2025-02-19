package com.johnp.sbfl;

import com.johnp.sbfl.bean.MethodInfo;
import com.johnp.sbfl.util.Analyzer;
import com.johnp.sbfl.util.FileExportUtil;
import com.johnp.sbfl.util.SuspicionProcessor;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class Runner {

    public static void main(String[] args) throws IOException {
        String defaultPathname = "C:\\Users\\John Pranoy Yalla\\OneDrive - Kennesaw State University\\Desktop\\Sem2\\Software Testing\\CoverageData\\NewCoverageData";
        int defaultFailCount = 50;

        String pathname = args.length > 0 ? args[0] : defaultPathname;
        int failCount = args.length > 1 ? Integer.parseInt(args[1]) : defaultFailCount;

        if (args.length == 0) {
            log.warn("No arguments provided. Hence using default values.");
            log.warn("Usage: java -jar JFreeSBFL.jar <filePath> <failCount>");
        }
       
        File folder = new File(pathname);

        // ** Read and Collect Data
        Map<String, MethodInfo> calculatedSuspicion = Analyzer.analyzeFolder(folder, failCount);

        // ** Sort Suspicion
        List<Map.Entry<String, MethodInfo>> sortedDataList = SuspicionProcessor.sortSuspicion(calculatedSuspicion);

        FileExportUtil.xlsExport("Suspicion.xlsx", sortedDataList);

        log.info("Completed Exporting Suspicion Data.");

    }



}
