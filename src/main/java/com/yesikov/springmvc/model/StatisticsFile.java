package com.yesikov.springmvc.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name="statistics_file")
public class StatisticsFile {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="content", nullable=false)
    private String content;

    @Column(name="number_of_lines", nullable=false)
    private int numberOfLines;

    @Column(name="longest_word", nullable=false)
    private int longestWord;

    @Column(name="shortest_word", nullable=false)
    private int shortestWord;

    @Column(name="total_length", nullable=false)
    private int totalLength;

    @Column(name="average_word_length", nullable=false)
    private double averageWordLength;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="file", nullable=false)
    private byte[] file;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getNumberOfLines() {
        return numberOfLines;
    }
    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }
    public int getLongestWord() {
        return longestWord;
    }
    public void setLongestWord(int longestWord) {
        this.longestWord = longestWord;
    }
    public int getShortestWord() {
        return shortestWord;
    }
    public void setShortestWord(int shortestWord) {
        this.shortestWord = shortestWord;
    }
    public int getTotalLength() {
        return totalLength;
    }
    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }
    public double getAverageWordLength() {
        return averageWordLength;
    }
    public void setAverageWordLength(double averageWordLength) {
        this.averageWordLength = averageWordLength;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticsFile that = (StatisticsFile) o;
        if (id != that.id) return false;
        if (numberOfLines != that.numberOfLines) return false;
        if (longestWord != that.longestWord) return false;
        if (shortestWord != that.shortestWord) return false;
        if (totalLength != that.totalLength) return false;
        if (Double.compare(that.averageWordLength, averageWordLength) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return Arrays.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + numberOfLines;
        result = 31 * result + longestWord;
        result = 31 * result + shortestWord;
        result = 31 * result + totalLength;
        temp = Double.doubleToLongBits(averageWordLength);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

}
