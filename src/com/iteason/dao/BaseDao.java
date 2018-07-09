package com.iteason.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	//增或改
	public void saveOrUpdate(T t);
	//增
	public void save(T t);
	//通过对象刪
	public void delete(T t);
	//通过id刪
	public void delete(Serializable id);//---->这是所有基本类型的实现类
	//通过对象改
	public void update(T t);
	//通过id查
	public T getById(Serializable id);
	//查所有记录
	public Number getTotalCount(DetachedCriteria dc);
	//分页查，返回用于分页的对象集合
	public List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
}
