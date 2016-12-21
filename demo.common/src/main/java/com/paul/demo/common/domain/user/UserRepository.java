/**
 * @Description: 
 * @ClassName: com.paul.demo.common.domain.user.UserRepository
 * @author: Paul Chen
 * @date: 2016年6月2日 下午5:21:13 
 */
package com.paul.demo.common.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 
 * @ClassName: com.paul.demo.common.domain.user.UserRepository
 * @author: Paul Chen
 * @date: 2016年6月2日 下午5:21:13 
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	
	
}
