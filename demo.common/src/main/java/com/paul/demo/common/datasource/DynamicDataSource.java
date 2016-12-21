/**
 * @Description: 
 * @ClassName: com.paul.demo.common.datasource.DynamicDataSource
 * @author: Paul Chen
 * @date: 2016年6月1日 下午5:44:47 
 */
package com.paul.demo.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description: 
 * @ClassName: com.paul.demo.common.datasource.DynamicDataSource
 * @author: Paul Chen
 * @date: 2016年6月1日 下午5:44:47 
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * @Title: determineCurrentLookupKey 
	 * @return
	 * @author: Paul Chen
	 * @date: 2016年6月1日 下午5:45:09
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceSwitcher.getDataSource();
	}

}
