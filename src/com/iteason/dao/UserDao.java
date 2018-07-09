package com.iteason.dao;

import com.iteason.domain.User;

public interface UserDao extends BaseDao<User> {

	User getUserByPassword(String user_code);

	User getUserByUserCode(String user_code);
	

}
