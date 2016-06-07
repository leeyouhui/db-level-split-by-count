package com.lyh.db.level.split.dao;

import org.apache.ibatis.annotations.Param;

import com.lyh.db.level.split.domain.UserCount;

public interface UserCountDao {
	
	/**
	 * 增加计数
	 * @return
	 */
	int increase();
	
	/**
	 * 获取当前计数和表名
	 * @Author: liyouhui
	 * @Date：2016年6月7日下午3:03:23
	 * @return
	 */
	UserCount getCurrentUserCount();
	
	int reset(UserCount userCount);

	int updateTableName(@Param("lastTableName")String lastTableName);
}
