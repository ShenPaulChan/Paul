/**
 * @Description: 
 * @ClassName: com.pual.demo.web.controller.TestController
 * @author: Paul Chen
 * @date: 2016年6月1日 下午4:31:47 
 */
package com.paul.demo.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paul.demo.common.domain.user.User;
import com.paul.demo.common.domain.user.UserRepository;
import com.paul.demo.common.exception.ResponseException;
import com.paul.demo.common.standardmsg.BaseFacade;
import com.paul.demo.common.standardmsg.Response;

/**
 * @Description: 
 * @ClassName: com.pual.demo.web.controller.TestController
 * @author: Paul Chen
 * @date: 2016年6月1日 下午4:31:47 
 *
 */
@Controller
@RequestMapping(value="test")
public class TestController extends BaseFacade {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/addTest", method=RequestMethod.GET, produces = "application/json")
	@Transactional
	public ModelAndView addTest(){
		ModelAndView mav = new ModelAndView("/index");
		User user = new User();
		user.setContent("水电费");
		user.setName("阿斯蒂芬");
		user.init(user);
		user = em.merge(user);
		System.out.println(user.getId());
		return mav;
	}
	
	@RequestMapping(value="/getTests", method=RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Response<Map<String, Object>> getTestList(Long testId){
		String hql = "FROM User u";
		@SuppressWarnings("unchecked")
		List<User> users = em.createQuery(hql).getResultList();
		Page<User> page = userRepository.findAll(new PageRequest(0, 3, new Sort(Direction.DESC, "createdOn")));
		users = page.getContent();
		System.out.println(page);
		System.out.println(page.getTotalPages());
		Map<String, Object> map = new HashMap<>();
		map.put("time", new Date());
		return new Response<Map<String, Object>>(map);
	}
	
	@ResponseBody
	@RequestMapping(value="/addTest", method=RequestMethod.POST , produces = "application/json; charset=UTF-8" , consumes = "application/json")
	public Response<Map<String, Object>> createTest(@RequestBody User user, @PathVariable String userId, @RequestParam String username){
		System.out.println(user.getCreatedOn());
		System.out.println(user.getName());
		Map<String, Object> map = new HashMap<>();
		map.put("time", user.getCreatedOn());
		return new Response<Map<String, Object>>(map);
	}
	
}
