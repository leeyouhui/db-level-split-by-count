package com.lyh.db.level.split.util;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lyh.db.level.split.domain.User;
import com.lyh.db.level.split.domain.UserCount;
import com.lyh.db.level.split.service.impl.UserCountServiceImpl;
import com.lyh.db.level.split.service.impl.UserServiceImpl;
@Service
public class UserUtil {
	public final static String TABLE_PREFIX = "user_";
	private static long dbRowMaxNum;
	static{
		ResourceBundle rb = ResourceBundle.getBundle("user");
		dbRowMaxNum = Long.parseLong(rb.getString("db.row.maxNum"));
	}
	
	@Autowired
	private UserCountServiceImpl userCountServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	public static User createUser(String name) {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName(name);
		user.setSex(1);
		user.setAge(24);
		user.setTel("12346789");
		user.setAddr("北京通州");
		user.setDsc("java开发工程师");
		user.setCreateTime(new Date());
//		user.setTableName(validCurrentCountAndReturnTableName());
		return user;
	}
	
	/**
	 * 判断当前计数，如果大于或等于配置中限制的数据库最大行数，就设置成0，并更新数据库，最后返回表名
	 * @Author: liyouhui
	 * @Date：2016年6月7日下午4:17:09
	 * @return
	 */
	public synchronized String validCurrentCountAndReturnTableName(){
		String tableName = null;
		try {
			//每插一条数据就查一次，待优化
			UserCount userCount = userCountServiceImpl.getCurrentUserCount();
			int currentCount = userCount.getCount();
			tableName = userCount.getTableName();
			if(currentCount >=  dbRowMaxNum || StringUtils.isEmpty(tableName)){
				tableName = initTableName();
				userServiceImpl.createNewTable(tableName);
				//重置计数表
				UserCount userCountNew = new UserCount();
				userCountNew.setTableName(tableName);
				userCountServiceImpl.reset(userCountNew);
			}
			userCountServiceImpl.increase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableName;
	}
	
	//不对
	public synchronized String validCurrentCountAndReturnTableName1(){
		String tableName = null;
		try {
			//每插一条数据就查一次，待优化
			int currentCount = UserCountServiceImpl.getCurrentCount();
			tableName = UserCountServiceImpl.getCurrentTableName();
			if(currentCount >=  dbRowMaxNum || StringUtils.isEmpty(tableName)){
				tableName = initTableName();
				userServiceImpl.createNewTable(tableName);
				//重置计数表
				UserCount userCountNew = new UserCount();
				userCountNew.setTableName(tableName);
				userCountServiceImpl.reset(userCountNew);
				UserCountServiceImpl.setCurrentTableName(tableName);
			}
			UserCountServiceImpl.setCurrentCount(++currentCount);
			userCountServiceImpl.increase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableName;
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
	
}
