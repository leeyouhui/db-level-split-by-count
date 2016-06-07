package com.lyh.db.level.split.timer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.lyh.db.level.split.domain.UserCount;
import com.lyh.db.level.split.service.UserService;
import com.lyh.db.level.split.service.impl.UserCountServiceImpl;
import com.lyh.db.level.split.util.UserUtil;

/**
 * 定时器(定时更新计数表中的表名字段)
 * @FileName:UserTimer.java 
 * @Author: liyouhui
 * @Date: 2016年6月6日 下午3:59:24
 * @since:  JDK 1.8
 */
@Component("userTimer")
@Lazy(false)
public class UserTimer {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCountServiceImpl userCountServiceImpl;
	
	@Scheduled(cron = "${job.order_user.cron}")
	public void runTask(){
		try {
			System.out.println("-----------check start------------");
			//定时检测计数表里的表名是否为空，如果为空则会查所有user，取一个最新表名存到计数表中
			UserCount userCount = userCountServiceImpl.getCurrentUserCount();
			if(userCount == null){
				return ;
			}
			if(StringUtils.isEmpty(userCount.getTableName())){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dbName", "user");
				List<String> tableNames = userService.getAllTableNames(map);
				if(tableNames == null || tableNames.size() <=0){
					return ;
				}
				String lastTableName = getLastTableName(tableNames);
				if(StringUtils.isEmpty(lastTableName)){
					return ;
				}
				userCountServiceImpl.updateTableName(lastTableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("-----------check end------------");
		}
		
	}
	
	/**
	 * 拿到最新的表名
	 * @param tableNames
	 * @return
	 */
	public String getLastTableName(List<String> tableNames){
		int[] ss = new int[tableNames.size()];
		for(int i = 0; i<tableNames.size();i++){
			String tableName = tableNames.get(i);
			if(StringUtils.isEmpty(tableName)) continue;
			ss[i] = Integer.parseInt(tableName.replaceAll(UserUtil.TABLE_PREFIX, ""));
			
		}
		Arrays.sort(ss);
		if(ss[ss.length-1] == 0){
			return null;
		}
		return UserUtil.TABLE_PREFIX + ss[ss.length-1];
	}
}
