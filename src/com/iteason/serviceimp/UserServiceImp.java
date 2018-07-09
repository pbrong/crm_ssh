package com.iteason.serviceimp;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.UserDao;
import com.iteason.domain.User;
import com.iteason.service.UserService;
import com.iteason.utils.MD5Utils;
@Service("userService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class UserServiceImp implements UserService {
	@Resource(name="userDao")
	private UserDao ud;
	
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public void saveUser(User u) {
		//通过user_code查询有无相同用户名
		User exitU = ud.getUserByUserCode(u.getUser_code());
		//有则抛异常
		if(exitU != null){
			throw new RuntimeException("用户名已存在");
		}else{
		//无则保存
		//md5加密
		 u.setUser_password(MD5Utils.md5(u.getUser_password()));
		 ud.save(u);
		}
	}


	public void setUd(UserDao ud) {
		this.ud = ud;
	}


	@Override
	public User getUserByPassword(User user) {
		User existU =  ud.getUserByPassword(user.getUser_code());
		if(existU == null){
			throw new RuntimeException("用户名不存在");
		}
		if(!existU.getUser_password().equals(MD5Utils.md5(user.getUser_password()))){
			throw new RuntimeException("密码错误");
		}
		
		return existU;
	}
	
	
	 
}
