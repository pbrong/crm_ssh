package com.iteason.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.iteason.domain.Customer;
import com.iteason.domain.LinkMan;
import com.iteason.service.CustomerService;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	
	
	private Customer customer = new Customer();
	@Resource(name="customerService")
	private CustomerService cs;
	//
	private File photo;
	
	private String photoFileName;
	
	private String photoContentType;
	



	private Integer currentPage;
	private Integer pageSize;
	
	public String list(){
		//封装离线查询对象
		try{
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		if(StringUtils.isNotBlank(customer.getCust_name())){
			dc.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		//1
		PageBean pb = cs.getPageBean(dc,currentPage,pageSize);
		//2
		ActionContext.getContext().put("pageBean", pb);
		ActionContext.getContext().put("cust_name", customer.getCust_name());
		}catch(Exception e){
			System.out.println(e);
		}
		return "list";
	}

	

	public String add() throws Exception {
		//upload
		photo.renameTo(new File("d:/upload.jsp"));
		System.out.println("filename"+photoFileName);
		System.out.println("filetype"+photoContentType);
		
		//添加客户
		cs.save(customer);
		return "toList";
	}
	
	public void addUI() throws Exception {
		//强制使Customer的增加选项通过Action拦截器
		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getServletContext().getContextPath()+"/jsp/customer/add.jsp");
		return;
	}
	
	public String toEdit() throws Exception {
		//toEdit page
		Customer c = cs.getById(customer.getCust_id());
		ActionContext.getContext().put("customer", c);
		return "toEdit";
	}
	
	public String delete() throws Exception {
		//删除客户
		cs.delete(customer.getCust_id());
		return "toList";
	}
	
	
	public String findLinkman() throws Exception{
		//编码表
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		//联系人的ajax回显
		//获取联系人列表
		List<LinkMan> list = cs.getAjaxList();
		System.out.println("-----------------------------"+list);
		/*LinkMan linkMan = new LinkMan();
		linkMan.setLkm_name("test");
		Customer c1 = new Customer();
		c1.setCust_id(1l);
		linkMan.setCustomer(c1);
		List<LinkMan> list1 = new  ArrayList<LinkMan>();
		list1.add(linkMan);*/
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println("-----------------------------"+json);
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}

	
	public String industryCount() throws Exception {
		List<Object[]> list = cs.getIndustryCount();
		ActionContext.getContext().put("list", list);
		return "industryCount";
	}


		@Override
		public Customer getModel() {
			// TODO Auto-generated method stub
			return customer;
		}
		
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

		public void setCs(CustomerService cs) {
			this.cs = cs;
		}


		public Integer getCurrentPage() {
			return currentPage;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		


		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		
		public File getPhoto() {
			return photo;
		}



		public void setPhoto(File photo) {
			this.photo = photo;
		}



		public String getPhotoFileName() {
			return photoFileName;
		}



		public void setPhotoFileName(String photoFileName) {
			this.photoFileName = photoFileName;
		}



		public String getFileContentType() {
			return photoContentType;
		}



		public void setFileContentType(String photoContentType) {
			this.photoContentType = photoContentType;
		}
	
}
