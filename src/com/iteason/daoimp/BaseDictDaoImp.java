package com.iteason.daoimp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.iteason.dao.BaseDictDao;
import com.iteason.domain.BaseDict;
@Repository("baseDictDao")
public class BaseDictDaoImp extends BaseDaoImp<BaseDict> implements BaseDictDao {

	@Resource(name="sessionFactory")//注入sessionFactroy
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		//封装离线查询对象 
		DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
		//封装条件
		dc.add(Restrictions.eq("dict_type_code", dict_type_code));
		//执行查询
		List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
		return list;
	}


}
