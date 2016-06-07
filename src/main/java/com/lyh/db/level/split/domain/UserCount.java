package com.lyh.db.level.split.domain;

import java.util.Date;
/**
 * 计数实体
 * @FileName:UserCount.java 
 * @Author: liyouhui
 * @Date: 2016年6月7日 下午3:02:07
 * @since:  JDK 1.8
 */
public class UserCount {
	private int count;
	private String tableName;
	private Date createTime;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
