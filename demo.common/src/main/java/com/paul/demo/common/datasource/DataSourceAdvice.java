/**
 * Copyright reserved by IZHUO.NET
 */
package com.paul.demo.common.datasource;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author Paul
 *
 * 2016-2-1
 */
public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
	
	private static Logger log = Logger.getLogger(DataSourceAdvice.class);
	
	// service方法执行之前被调用  
    public void before(Method method, Object[] args, Object target) throws Throwable {  
       // log.info("切入点: " + target.getClass().getName() + "类中" + method.getName() + "方法");
    	DataSourceSwitcher.setMaster();
    	return;
    	
    	
        /*if(method.getName().startsWith("add")   
            || method.getName().startsWith("create")  
            || method.getName().startsWith("save")  
            || method.getName().startsWith("edit")  
            || method.getName().startsWith("update")  
            || method.getName().startsWith("execute")
            || method.getName().startsWith("delete")  
            || method.getName().startsWith("remove")){  
            log.info("切换到: master");  
            DataSourceSwitcher.setMaster();  
        }  
        else  {  
            //log.info("切换到: slave");  
            DataSourceSwitcher.setSlave();  
        } */ 
    }  
  
    // service方法执行完之后被调用  
    public void afterReturning(Object arg0, Method method, Object[] args, Object target) throws Throwable { 
    	
    }  
  
    // 抛出Exception之后被调用  
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {  
        DataSourceSwitcher.setMaster();
        log.info("出现异常,切换到: master");  
    }  
}
