package com.iteason.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.iteason.domain.Customer;
import com.iteason.domain.LinkMan;

public interface CustomerDao extends BaseDao<Customer>{

	List<LinkMan> getAjaxList();

	//按照行业统计客户数量
	List getIndustryCount();
}
