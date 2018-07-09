package com.iteason.service;

import org.hibernate.criterion.DetachedCriteria;

import com.iteason.domain.SaleVisit;
import com.iteason.utils.PageBean;

public interface SaleVisitService {

	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);


	public void save(SaleVisit sv);


	public SaleVisit getById(String visit_id);

}
