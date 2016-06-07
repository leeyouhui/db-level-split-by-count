package com.lyh.db.level.split.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.db.level.split.dao.UserCountDao;
import com.lyh.db.level.split.domain.UserCount;
import com.lyh.db.level.split.service.UserCountService;

@Service
public class UserCountServiceImpl implements UserCountService {

	@Autowired
	private UserCountDao userCountDao;
	
	@Override
	public synchronized int increase() {
		return userCountDao.increase();
	}

	@Override
	public synchronized UserCount getCurrentUserCount() {
		return userCountDao.getCurrentUserCount();
	}

	@Override
	public synchronized int reset(UserCount userCount) {
		return userCountDao.reset(userCount);
	}
	
	@Override
	public int updateTableName(String lastTableName) {
		return userCountDao.updateTableName(lastTableName);	
	}

}
