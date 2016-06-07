package com.lyh.db.level.split.domain;

import java.util.List;

public class BaseDomain {
	String tableName;
	List<String> tableNames;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getTableNames() {
		return tableNames;
	}

	public void setTableNames(List<String> tableNames) {
		this.tableNames = tableNames;
	}

}
