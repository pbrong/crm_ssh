package com.iteason.daoimp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.iteason.dao.BaseDao;

public class BaseDaoImp<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	
	private Class clazz;//用于接受运行期泛型类型
	
	public BaseDaoImp(){
		//获得当前类型的带有泛型类型的父类
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得运行期的泛型类型
		clazz = (Class) pt.getActualTypeArguments()[0];
	}
	
	
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void delete(Serializable id) {
		 T t = this.getById(id);//先取
		 getHibernateTemplate().delete(t);//再删
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
		
	}

	@Override
	public T getById(Serializable id) {
		
		return (T) getHibernateTemplate().get(clazz, id);
	}

	@Override
	public Number getTotalCount(DetachedCriteria dc) {
		//聚合函数，查数量
		 dc.setProjection(Projections.rowCount());
		 List<Long> count = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		 dc.setProjection(null);//清空聚合函数,不清空的话会有条件的残留
		 if(count != null && count.size() > 0){
			 return count.get(0).intValue();
		 }else{
			 return null;
		 }
	}

	@Override
	public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start,pageSize);
		return list;
	}


	@Override
	public void saveOrUpdate(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(t);
	}

}
