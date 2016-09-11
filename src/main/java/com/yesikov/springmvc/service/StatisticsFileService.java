package com.yesikov.springmvc.service;


import com.yesikov.springmvc.model.StatisticsFile;

import java.util.List;

public interface StatisticsFileService {

    StatisticsFile findById(int id);

    void saveStatisticsFile(StatisticsFile statisticsFile);

    List<StatisticsFile> findAllStatisticsFiles();

}
