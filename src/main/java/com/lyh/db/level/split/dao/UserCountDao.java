package com.lyh.db.level.split.dao;

import com.lyh.db.level.split.domain.UserCount;

public interface UserCountDao {
	
	int update(UserCount userCount);
	
	/**
	 * 获取当前计数和表名
	 * @Author: liyouhui
	 * @Date：2016年6月7日下午3:03:23
	 * @return
	 */
	UserCount getCurrentUserCount();
	
	int reset();
}
