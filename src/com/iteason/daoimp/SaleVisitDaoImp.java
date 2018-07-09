package com.iteason.daoimp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.iteason.dao.SaleVisitDao;
import com.iteason.domain.SaleVisit;
@Repository("saleVisitDao")
public class SaleVisitDaoImp extends BaseDaoImp<SaleVisit> implements SaleVisitDao {
	
	@Resource(name="sessionFactory")//注入sessionFactroy
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
}
