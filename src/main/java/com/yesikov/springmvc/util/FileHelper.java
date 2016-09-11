package com.yesikov.springmvc.util;


import com.yesikov.springmvc.model.FileBucket;
import com.yesikov.springmvc.model.StatisticsFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {
    public static File createFile(String fileName, String content) throws IOException {
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, content);
        return file;
    }

    public static String getContent(FileBucket fileBucket) throws IOException {
        ByteArrayInputStream stream = new   ByteArrayInputStream(fileBucket.getFile().getBytes());
        return IOUtils.toString(stream, "UTF-8");
    }

    public static List<StatisticsFile> getFilesByCondition(String condition, List<StatisticsFile> allStatisticsFiles) {
        switch (condition) {
            case "moreTenLines" : return getFilesMoreTenLines(allStatisticsFiles);
            case "lessFiveLines": return getFilesLessFiveLines(allStatisticsFiles);
            case "longestWord"  : return getFileWithLongestWord(allStatisticsFiles);
            case "shortestWord" : return getFileWithShortestWord(allStatisticsFiles);
        }
        return Collections.emptyList();
    }

    private static List<StatisticsFile> getFilesMoreTenLines(List<StatisticsFile> allStatisticsFiles) {
        return allStatisticsFiles.stream().filter(statFile -> statFile.getNumberOfLines() >= 10).collect(Collectors.toList());
    }

    private static List<StatisticsFile> getFilesLessFiveLines(List<StatisticsFile> allStatisticsFiles) {
        return allStatisticsFiles.stream().filter(statFile -> statFile.getNumberOfLines() <= 5).collect(Collectors.toList());
    }

    private static List<StatisticsFile> getFileWithLongestWord(List<StatisticsFile> allStatisticsFiles) {
        Comparator<StatisticsFile> comparator = (statFile1, statFile2) -> Integer.compare(statFile1.getLongestWord(), statFile2.getLongestWord());
        return Arrays.asList(allStatisticsFiles.stream().max(comparator).get());
    }

    private static List<StatisticsFile> getFileWithShortestWord(List<StatisticsFile> allStatisticsFiles) {
        Comparator<StatisticsFile> comparator = (statFile1, statFile2) -> Integer.compare(statFile1.getShortestWord(), statFile2.getShortestWord());
        return Arrays.asList(allStatisticsFiles.stream().min(comparator).get());
    }
}
