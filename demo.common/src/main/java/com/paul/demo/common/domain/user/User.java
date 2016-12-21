/**
 * @Description: 
 * @ClassName: com.paul.demo.common.domain.User
 * @author: Paul Chen
 * @date: 2016年6月1日 下午6:20:28 
 */
package com.paul.demo.common.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.paul.demo.common.domain.Auditor;

/**
 * @Description: 
 * @ClassName: com.paul.demo.common.domain.User
 * @author: Paul Chen
 * @date: 2016年6月1日 下午6:20:28 
 *
 */
@Entity
@Table(name = "user")
@Component
public class User extends Auditor {
	
	
	@Column(name="username", length=20, columnDefinition="varchar(20) default ''")
	private String name;
	@Type(type="text")
	private String content;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
