package com.iteason.serviceimp;

import java.util.List;


import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.LinkManDao;
import com.iteason.domain.LinkMan;
import com.iteason.service.LinkManService;
import com.iteason.utils.PageBean;
@Service("linkManService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class LinkManServiceImp implements LinkManService {
	@Resource(name="linkManDao")
	private LinkManDao lmd;	
	
	@Override
	public void save(LinkMan linkman) {
		lmd.save(linkman);
	}
	
	@Override
	public LinkMan getById(Long lkm_id) {
		return lmd.getById(lkm_id);
	}

	

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//调用dao查询总记录
		Integer totalCount = (Integer) lmd.getTotalCount(dc);
		//创建pageBean对象
		PageBean pb = new PageBean(currentPage,totalCount,pageSize);
		//调用dao查询分页数据
		List<LinkMan> list = lmd.getPageList(dc,pb.getStart(),pb.getPageSize());
		//列表数据放入pageBean中并返回
		pb.setList(list);
		return pb;
	}
	
		public void setLmd(LinkManDao lmd) {
			this.lmd = lmd;
		}



		
	
}
