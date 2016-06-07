package com.lyh.db.level.split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.lyh.db.level.split.domain.User;
import com.lyh.db.level.split.service.UserService;
import com.lyh.db.level.split.service.impl.UserServiceImpl;
import com.lyh.db.level.split.util.UserUtil;

public class UserServiceImplTest {

	private static ApplicationContext context;
	private static UserService userService;
	private static UserUtil userUtil;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("spring.xml");
		userService = context.getBean(UserServiceImpl.class);
		userUtil = context.getBean(UserUtil.class);
	}

	@Test
	public void testInsert() {
		int i = 0;
		while(i < 5){
			User user = UserUtil.createUser();
			user.setTableName(userUtil.validCurrentCountAndReturnTableName());
			System.out.println(userService.insert(user));
			i++;
		}
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testSelectById() {
	}

	@Test
	public void testCountUser() {
	}

	@Test
	public void testListPageUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dbName", "user");
		map.put("tableName", "user_1");
		List<String> tableNames = userService.getAllTableNames(map);
		if( ! StringUtils.isEmpty(tableNames)){
			map.put("tableNames", tableNames);
		}
		int count = userService.countUser(map);
		System.out.println("----count = "+count);
		if(count > 0){
			List<User> users = userService.listPageUser(map);
			if(users != null && users.size() > 0){
				for(User u:users){
					System.out.println(u.toString());
				}
			}
		}
	}

	@Test
	public void testCreateNewTable() {
		userService.createNewTable(UserUtil.initTableName());
	}

	@Test
	public void testGetAllTableNames() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dbName", "user");
		List<String> tableNames = userService.getAllTableNames(map);
		for(String tableName : tableNames){
			System.out.println(tableName);
		}
	}

}
