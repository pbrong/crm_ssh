package com.iteason.web.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;

import com.iteason.domain.User;
import com.iteason.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.ws.developer.SchemaValidation;
@Controller("userAction")
@Scope("prototype")
@ContextConfiguration("classpath:applicationContext.xml")
public class UserAction extends ActionSupport  implements ModelDriven<User> {
	private User user = new User();
	@Resource(name="userService")
	private UserService userService;
	
	public String login(){
		User u = null;
		//1、调用service执行登陆逻辑
		try {
			 u = userService.getUserByPassword(user);
		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().put("error", e.getMessage());
			return "loginError";
		}
		
		//2、将返回的User对象放入session域中
		ActionContext.getContext().getSession().put("user", u);
		//3、重定向到项目首页
		return "toHome";
	}
	
	public String regist(){
		//1、调用service执行注册逻辑
		try{
		userService.saveUser(user);
		System.out.println(user.getUser_code());
		}catch(Exception e){
			e.printStackTrace();
			ActionContext.getContext().put("error", e.getMessage());
			return "registError";
		}
		return "toLogin";
	
	}
	
	public String logout(){
		ActionContext.getContext().getSession().put("user", null);
		return "toLogin";
	}
	
	
		@Override
		public User getModel() {
			return user;
		}

		public void setUserService(UserService userService) {
			this.userService = userService;
		}
 
	
}
