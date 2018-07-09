package com.iteason.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.CustomerDao;
import com.iteason.domain.Customer;
import com.iteason.domain.LinkMan;
import com.iteason.service.CustomerService;
import com.iteason.utils.PageBean;
@Service("customerService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class CustomerServiceImp implements CustomerService {
	@Resource(name="customerDao")
	private CustomerDao cd;

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
				//调用dao查询总记录
				Integer totalCount = (Integer) cd.getTotalCount(dc);
				//创建pageBean对象
				PageBean pb = new PageBean(currentPage,totalCount,pageSize);
				//调用dao查询分页数据
				
				List<Customer> list = cd.getPageList(dc,pb.getStart(),pb.getPageSize());
				//列表数据放入pageBean中并返回
				System.out.println(list.get(0).getCust_name());
				pb.setList(list);
				return pb;
	}
	
			@Override
			public void save(Customer customer) {
				//1、维护Customer与数据字典对象的关系
				//2、调用Dao保存用户
				cd.save(customer);
				
			}
		
			public void setCd(CustomerDao cd) {
				this.cd = cd;
			}

			@Override
			public Customer saveOrUpdate(Customer customer) {
				cd.saveOrUpdate(customer);
				return null;
			}

			@Override
			public Customer getById(Long cust_id) {
				
				return cd.getById(cust_id);
			}

			@Override
			public List<LinkMan> getAjaxList() {
				// 查询数据库获得所有linkman
				return cd.getAjaxList();
			}

			@Override
			public void delete(Long cust_id) {
				// 删除用户
				cd.delete(cust_id);
			}

			@Override
			public List<Object[]> getIndustryCount() {
				return cd.getIndustryCount();
			}

	

}
