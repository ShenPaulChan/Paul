/**
 * @Description: 
 * @ClassName: com.paul.demo.common.standardmsg.BaseFacade
 * @author: Paul Chen
 * @date: 2016年6月2日 下午4:22:24 
 */
package com.paul.demo.common.standardmsg;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paul.demo.common.exception.ResponseException;

/**
 * @Description: 
 * @ClassName: com.paul.demo.common.standardmsg.BaseFacade
 * @author: Paul Chen
 * @date: 2016年6月2日 下午4:22:24 
 *
 */
public class BaseFacade {

	@Autowired
	ResourceBundleMessageSource msgResource;
	
	@ExceptionHandler(ResponseException.class)
	public @ResponseBody Response<String> handleFkbException(ResponseException e, HttpServletRequest req) {
		return new Response<String>(msgResource.getMessage(e.getMsgId(), new String[] {}, req.getLocale()), 500);
	}
	
	
}
