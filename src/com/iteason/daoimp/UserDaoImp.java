package com.iteason.daoimp;



import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.iteason.dao.UserDao;
import com.iteason.domain.User;
@Repository("userDao")
//为HibernateDaoSupport注入sessionFactory
public class UserDaoImp extends BaseDaoImp<User> implements UserDao {
	
	@Resource(name="sessionFactory")//注入sessionFactroy
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	

	@Override
	public User getUserByPassword(String user_code) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("user_code", user_code));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		if(list != null &&list.size() > 0){
			return list.get(0);
		}else{
		return null;
		}
	}

	@Override
	public User getUserByUserCode(String user_code) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("user_code", user_code));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		if(list != null &&list.size() > 0){
			return list.get(0);
		}else{
		return null;
		}
	}


}
