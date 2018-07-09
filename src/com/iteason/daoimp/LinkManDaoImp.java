package com.iteason.daoimp;

import javax.annotation.Resource;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.iteason.dao.LinkManDao;
import com.iteason.domain.LinkMan;
@Repository("linkManDao")
public class LinkManDaoImp extends BaseDaoImp<LinkMan> implements LinkManDao {
	@Resource(name="sessionFactory")//注入sessionFactroy
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
}
