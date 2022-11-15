package com.comestic.dao;

import com.comestic.model.StatisticModel;
import com.comestic.utils.Paging;

public interface IStatisticDAO {
	StatisticModel getStatistic(Paging paging);
	Long getTotalAccess();
	void updateTotalAccess();
}
