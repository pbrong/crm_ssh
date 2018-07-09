package com.iteason.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Customer;
import com.iteason.domain.SaleVisit;
import com.iteason.domain.User;
import com.iteason.service.SaleVisitService;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("saleVisitAction")
@Scope("prototype")
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{
	private SaleVisit sv = new SaleVisit();
	@Resource(name="saleVisitService")
	private SaleVisitService svs;
	

	private Integer currentPage;
	private Integer pageSize;
	
	
	//添加客户拜访记录
	public String add() throws Exception {
		//取出登陆用户放入SaleVisit实体中表达关心
		User u = (User) ActionContext.getContext().getSession().get("user");
		//调用service方法保存客户列表记录
		sv.setUser(u);
		svs.save(sv);
		//重定向到客户列表Action
		return "toList";
	}

	public String list(){
		//封装离线查询对象
		try{
		DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
		if(sv.getCustomer() != null && sv.getCustomer().getCust_id() != null){
			dc.add(Restrictions.eq("customer.cust_id", sv.getCustomer().getCust_id()));
		}
		//1
		PageBean pb = svs.getPageBean(dc,currentPage,pageSize);
		//2
		ActionContext.getContext().put("pageBean", pb);
		}catch(Exception e){
			System.out.println(e);
		}
		return "list";
	}
	
	public void addUI() throws Exception {
		//强制使SaleVisit的增加选项通过Action拦截器
		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getServletContext().getContextPath()+"/jsp/salevisit/add.jsp");
		return;
	}
	
	public String toEdit() throws Exception {
		SaleVisit saleVisit = svs.getById(sv.getVisit_id());
		ActionContext.getContext().put("saleVisit", saleVisit);
		return "toEdit";
	}

	@Override
	public SaleVisit getModel() {
		return sv;
	}




	public void setSvs(SaleVisitService svs) {
		this.svs = svs;
	}


	public Integer getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
