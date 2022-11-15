package com.comestic.service.implement;

import javax.inject.Inject;

import com.comestic.dao.IStatisticDAO;
import com.comestic.model.StatisticModel;
import com.comestic.service.IStatisticService;
import com.comestic.utils.Paging;

public class StatisticService implements IStatisticService {

	@Inject
	private IStatisticDAO statisticDAO;
	
	@Override
	public StatisticModel getStatistic(Paging paging) {
		return statisticDAO.getStatistic(paging);
	}

	@Override
	public Long getTotalAccess() {
		return statisticDAO.getTotalAccess();
	}

	@Override
	public void updateTotalAccess() {
		statisticDAO.updateTotalAccess();
	}

}
