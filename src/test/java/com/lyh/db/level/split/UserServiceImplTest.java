package com.lyh.db.level.split;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyh.db.level.split.domain.User;
import com.lyh.db.level.split.service.UserService;
import com.lyh.db.level.split.service.impl.UserServiceImpl;
import com.lyh.db.level.split.util.UserUtil;

public class UserServiceImplTest {

	private static ApplicationContext context;
	private static UserService userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("spring.xml");
		userService = context.getBean(UserServiceImpl.class);
	}

	@Test
	public void testInsert() {
		int i = 0;
		while(i < 5){
			User user = UserUtil.createUser();
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
	}

	@Test
	public void testCreateNewTable() {
		userService.createNewTable(UserUtil.initTableName());
	}

	@Test
	public void testGetAllTableNames() {
	}

}
