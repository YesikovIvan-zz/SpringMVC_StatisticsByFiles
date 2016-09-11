package com.yesikov.springmvc.dao.impl;


import com.yesikov.springmvc.dao.AbstractDao;
import com.yesikov.springmvc.dao.StatisticsFileDao;
import com.yesikov.springmvc.model.StatisticsFile;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("statisticsFileDao")
public class StatisticsFileDaoImpl extends AbstractDao<Integer, StatisticsFile> implements StatisticsFileDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<StatisticsFile> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<StatisticsFile>)crit.list();
    }

    @Override
    public StatisticsFile findById(int id) {
        StatisticsFile statisticsFile = getByKey(id);
		return statisticsFile;
    }

    @Override
    public void save(StatisticsFile statisticsFile) {
        persist(statisticsFile);
    }

}
