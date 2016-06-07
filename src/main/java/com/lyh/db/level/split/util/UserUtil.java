package com.lyh.db.level.split.util;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.db.level.split.domain.User;
import com.lyh.db.level.split.service.impl.UserCountServiceImpl;
@Service
public class UserUtil {
	private final static String TABLE_PREFIX = "user_";
	private static long dbRowMaxNum;
	static{
		ResourceBundle rb = ResourceBundle.getBundle("user");
		dbRowMaxNum = Long.parseLong(rb.getString("db.row.maxNum"));
	}
	
	@Autowired
	private UserCountServiceImpl userCountServiceImpl;
	
	public static User createUser() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("name"+new Date().getTime());
		user.setSex(111111);
		user.setAge(24);
		user.setTel("12346789");
		user.setAddr("北京通州");
		user.setDsc("java开发工程师");
		user.setCreateTime(new Date());
//		user.setTableName(getDbTableName());
		return user;
	}
	
	/**
	 * 获取当前计数，如果大于或等于配置中限制的数据库最大行数，就设置成0，并更新数据库
	 * @Author: liyouhui
	 * @Date：2016年6月7日下午4:17:09
	 * @return
	 */
	public int getCurrentCount(){
		int currentCount = UserCountServiceImpl.currentCount;
		if(currentCount > dbRowMaxNum){
			userCountServiceImpl.reset();
		}
		return currentCount;
	}
	
	/**
	 * 初始表名
	 * @Author: liyouhui
	 * @Date：2016年6月6日下午12:46:56
	 * @param date
	 * @return
	 */
	public static String initTableName(){
		return TABLE_PREFIX + new Date().getTime();
	}
	
	/**
	 * 根据时间范围匹配表名（多）
	 * @Author: liyouhui
	 * @Date：2016年6月6日下午5:13:59
	 * @param param
	 * @return
	 */
	public static List<String> matchTableNames(Map<String, Object> param){
		List<String> tableNames = null;
		return tableNames;
	}
	
}
