/**
 * Copyright reserved by IZHUO.NET
 */
package com.paul.demo.common.datasource;

import org.springframework.util.Assert;

/**
 * @author Paul
 *
 * 2016-2-1
 */
public class DataSourceSwitcher {

	@SuppressWarnings("rawtypes")
	private static final ThreadLocal contextHolder = new ThreadLocal();

	@SuppressWarnings("unchecked")
	public static void setDataSource(String dataSource) {
		Assert.notNull(dataSource, "dataSource cannot be null");
		contextHolder.set(dataSource);
	}

	public static void setMaster(){
		clearDataSource();
    }
	
	public static void setSlave() {
		setDataSource("slave");
	}
	
	public static String getDataSource() {
		return (String) contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
	
}
