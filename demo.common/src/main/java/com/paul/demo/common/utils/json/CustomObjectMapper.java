/**
 * @Description: 
 * @ClassName: com.paul.demo.common.utils.json.CustomObjectMapper
 * @author: Paul Chen
 * @date: 2016年6月2日 下午5:56:28 
 */
package com.paul.demo.common.utils.json;



import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * @Description: 
 * @ClassName: com.paul.demo.common.utils.json.CustomObjectMapper
 * @author: Paul Chen
 * @date: 2016年6月2日 下午5:56:28 
 *
 */
public class CustomObjectMapper extends ObjectMapper {

	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 928237847334006565L;

	public CustomObjectMapper(){
		super(null, null, null);
		this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

}
