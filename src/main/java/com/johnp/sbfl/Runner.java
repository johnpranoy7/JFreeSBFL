package com.johnp.sbfl;

import com.johnp.sbfl.bean.MethodInfo;
import com.johnp.sbfl.util.Analyzer;
import com.johnp.sbfl.util.FileExportUtil;
import com.johnp.sbfl.util.SuspicionProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Runner {

    public static void main(String[] args) throws IOException {
        String pathname = "C:\\Users\\John Pranoy Yalla\\OneDrive - Kennesaw State University\\Desktop\\Sem2\\Software Testing\\CoverageData\\NewCoverageData";
        final int failCount = 50;

        File folder = new File(pathname);

        // ** Read and Collect Data
        Map<String, MethodInfo> calculatedSuspicion = Analyzer.analyzeFolder(folder, failCount);

        // ** Sort Suspicion
        List<Map.Entry<String, MethodInfo>> sortedDataList = SuspicionProcessor.sortSuspicion(calculatedSuspicion);

        FileExportUtil.xlsExport("Suspicion.xlsx", sortedDataList);

    }



}
