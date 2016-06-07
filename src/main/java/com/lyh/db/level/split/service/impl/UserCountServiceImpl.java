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
	
	//当前计数(每次加载文件)
	public static int currentCount;
	
	{
		currentCount = this.getCurrentUserCount().getCount();
	}
	
	@Override
	public synchronized int update(UserCount userCount) {
		return userCountDao.update(userCount);
	}

	@Override
	public synchronized UserCount getCurrentUserCount() {
		return userCountDao.getCurrentUserCount();
	}

	@Override
	public synchronized int reset() {
		return userCountDao.reset();
	}

}
