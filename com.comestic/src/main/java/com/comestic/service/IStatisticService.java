package com.comestic.service;

import com.comestic.model.StatisticModel;
import com.comestic.utils.Paging;

public interface IStatisticService {
	StatisticModel getStatistic(Paging paging);
	Long getTotalAccess();
	void updateTotalAccess();
}
