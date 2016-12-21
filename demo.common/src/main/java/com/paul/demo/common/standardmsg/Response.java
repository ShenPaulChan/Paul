/**
 * 
 */
package com.paul.demo.common.standardmsg;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @Description: 
 * @ClassName: com.paul.demo.common.standardmsg.Response
 * @author: Paul Chen
 * @date: 2016年6月2日 下午5:15:24 
 *
 * @param <T>
 */
@XmlRootElement
public class Response<T> {
	
	private int statusCode;
	private String msg;
	private T data;

	public Response(){
		
	}
	@SuppressWarnings("unchecked")
	public Response(String msg, int code) {
		this.statusCode = code;
		this.msg = msg;
		this.data = (T) msg;
	}
	
	public Response(T t){
		this.statusCode = 200;
		this.msg = "success";
		this.data = t;
	}
	
	public Response(int statusCode, T data) {
		this.statusCode = statusCode;
		this.msg = "";
		this.data = data;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
