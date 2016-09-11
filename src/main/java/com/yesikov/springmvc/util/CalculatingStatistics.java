package com.yesikov.springmvc.util;

import com.yesikov.springmvc.model.StatisticsFile;
import com.yesikov.springmvc.model.FileBucket;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;

public class CalculatingStatistics {

    private CalculatingStatistics() {
        //util class
    }

    public static StatisticsFile getStatisticsFile(FileBucket fileBucket) throws IOException {
        StatisticsFile statisticsFiles = new StatisticsFile();
        String content = (fileBucket.getContent()!=null ) ? fileBucket.getContent() : FileHelper.getContent(fileBucket);
        if(fileBucket.getFile() != null){
            statisticsFiles.setName(fileBucket.getFile().getOriginalFilename());
            statisticsFiles.setFile(fileBucket.getFile().getBytes());
        } else {
            File file = FileHelper.createFile(fileBucket.getName()+".txt", content);
            statisticsFiles.setName(file.getName());
            statisticsFiles.setFile(FileUtils.readFileToByteArray(file));
        }
        statisticsFiles.setContent(content);
        statisticsFiles.setNumberOfLines(getNumberOfLines(content));
        statisticsFiles.setLongestWord(getLongestWord(content));
        statisticsFiles.setShortestWord(getShortestWord(content));
        statisticsFiles.setTotalLength(getTotalLength(content));
        statisticsFiles.setAverageWordLength(getAverageWordLength(content));
        return statisticsFiles;
    }

    private static int getLongestWord(String content) {
        String max =  Arrays.stream(splitString(content)).max((a, b) -> a.length() - b.length()).get();
        return max.length();
    }

    private static int getShortestWord(String content) {
        String min =  Arrays.stream(splitString(content)).min((a, b) -> a.length() - b.length()).get();
        return min.length();
    }

    private static double getAverageWordLength(String content) {
        int count = 0;
        double sum = 0;
        double average = 0;
        String[] words = splitString(content);

        for (String word : words) {
            double wordLength = word.length();
            sum += wordLength;
            count++;
        }
        if (count > 0) {
            average = sum / count;
        }
        return average;
    }

    private static int getNumberOfLines(String content) {
        return  content.split("\r?\n").length;
    }

    private static int getTotalLength(String content) {
        return content.length();
    }

    private static String[] splitString(String content){
        return content.replaceAll("[.,:!?]", "").split("\\s+");
    }
}
