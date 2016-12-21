/**
 * @Description: 
 * @ClassName: com.paul.demo.common.exception.ResponseException
 * @author: Paul Chen
 * @date: 2016年6月2日 下午4:24:19 
 */
package com.paul.demo.common.exception;

/**
 * @Description: 
 * @ClassName: com.paul.demo.common.exception.ResponseException
 * @author: Paul Chen
 * @date: 2016年6月2日 下午4:24:19 
 *
 */
public class ResponseException extends RuntimeException {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5891848807294533399L;

	private String msgId;
	
	public ResponseException(String msgId){
		this.msgId = msgId;
	}
	
	public ResponseException(String msgId,String msg){
		super(msg);
		this.msgId = msgId;
	}
	
	public String getMsgId(){
		return msgId;
	}
	
}
