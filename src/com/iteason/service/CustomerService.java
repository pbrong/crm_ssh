package com.iteason.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.iteason.domain.Customer;
import com.iteason.domain.LinkMan;
import com.iteason.utils.PageBean;

public interface CustomerService {

	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	public void save(Customer customer);
	public Customer saveOrUpdate(Customer customer);
	public Customer getById(Long cust_id);
	public List<LinkMan> getAjaxList();
	public void delete(Long cust_id);
	public List<Object[]> getIndustryCount();
}
