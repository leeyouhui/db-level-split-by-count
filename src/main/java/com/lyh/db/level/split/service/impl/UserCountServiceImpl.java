package com.lyh.db.level.split.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.db.level.split.dao.UserCountDao;
import com.lyh.db.level.split.domain.UserCount;
import com.lyh.db.level.split.service.UserCountService;

@Service
public class UserCountServiceImpl implements UserCountService,InitializingBean{

	@Autowired
	private UserCountDao userCountDao;
	
	//当前计数
	private static int currentCount;
	
	private static String currentTableName;
	
	
	@Override
	public void afterPropertiesSet() throws Exception 
	{
		if(userCountDao != null){
			UserCount userCount = userCountDao.getCurrentUserCount();
			if(userCount != null){
				setCurrentCount(userCount.getCount());
				setCurrentTableName(userCount.getTableName());
			}
		}
		
	}
	
	public synchronized static int getCurrentCount() {
		return currentCount;
	}

	public synchronized static void setCurrentCount(int currentCount) {
		UserCountServiceImpl.currentCount = currentCount;
	}
	
	public synchronized static String getCurrentTableName() {
		return currentTableName;
	}

	public synchronized static void setCurrentTableName(String currentTableName) {
		UserCountServiceImpl.currentTableName = currentTableName;
	}
	
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
