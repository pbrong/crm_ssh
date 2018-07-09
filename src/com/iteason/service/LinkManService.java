package com.iteason.service;

import org.hibernate.criterion.DetachedCriteria;

import com.iteason.domain.LinkMan;
import com.iteason.utils.PageBean;

public interface LinkManService {

	void save(LinkMan linkman);

	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	LinkMan getById(Long lkm_id);

}
