package com.iteason.daoimp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.iteason.dao.CustomerDao;
import com.iteason.domain.Customer;
import com.iteason.domain.LinkMan;
@Repository("customerDao")
public class CustomerDaoImp extends BaseDaoImp<Customer> implements CustomerDao{
	@Resource(name="sessionFactory")//注入sessionFactroy
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	@Override
	public List<LinkMan> getAjaxList() {
		//查询数据库获得所有linkman
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		return (List<LinkMan>) getHibernateTemplate().findByCriteria(dc);
	}
	
	
	@Override
	@SuppressWarnings("all")
	public List<Object[]> getIndustryCount() {
		//原生sql查询,多表查询
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
			String sql = "SELECT bd.dict_type_name 行业性质 ,COUNT(*) 总数 FROM cst_customer c,base_dict bd WHERE c.`cust_industry` = bd.`dict_id` GROUP BY c.`cust_industry`";
			@Override
			public List doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			} 	
		});
		 return list;
	}

}
