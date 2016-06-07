package com.lyh.db.level.split.service;

import com.lyh.db.level.split.domain.UserCount;

public interface UserCountService {
	
	/**
	 * 增加计数
	 * @Author: liyouhui
	 * @Date：2016年6月7日下午4:33:05
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

	/**
	 * 重置
	 * @Author: liyouhui
	 * @Date：2016年6月7日下午4:32:55
	 * @return
	 */
	int reset(UserCount userCount);

	int updateTableName(String lastTableName);
}
