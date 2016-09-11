package com.yesikov.springmvc.dao;

import com.yesikov.springmvc.model.StatisticsFile;

import java.util.List;


public interface StatisticsFileDao {

    List<StatisticsFile> findAll();

    StatisticsFile findById(int id);

    void save(StatisticsFile statisticsFile);
}
