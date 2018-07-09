package com.iteason.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.SaleVisitDao;
import com.iteason.domain.Customer;
import com.iteason.domain.SaleVisit;
import com.iteason.service.SaleVisitService;
import com.iteason.utils.PageBean;
@Service("saleVisitService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class SaleVisitServiceImp implements SaleVisitService {
	@Resource(name="saleVisitDao")
	private SaleVisitDao svd;
	
	@Override
	public void save(SaleVisit sv) {
		svd.save(sv);
	}

	

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//调用dao查询总记录
		Integer totalCount = (Integer) svd.getTotalCount(dc);
		//创建pageBean对象
		PageBean pb = new PageBean(currentPage,totalCount,pageSize);
		//调用dao查询分页数据
		
		List<SaleVisit> list = (List<SaleVisit>)svd.getPageList(dc,pb.getStart(),pb.getPageSize());
		//列表数据放入pageBean中并返回
		pb.setList(list);
		return pb;
	}
	
	
	@Override
	public SaleVisit getById(String visit_id) {
		SaleVisit salevisit = svd.getById(visit_id);
		return salevisit;
	}
	
	public void setSvd(SaleVisitDao svd) {
		this.svd = svd;
	}



	


	
	
}
