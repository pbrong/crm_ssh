package com.iteason.web.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Customer;
import com.iteason.domain.LinkMan;
import com.iteason.service.CustomerService;
import com.iteason.service.LinkManService;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("linkManAction")
@Scope("prototype")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	
	
	
	private LinkMan linkman = new LinkMan();
	@Resource(name="linkManService")
	private LinkManService lms;

	private Integer currentPage;
	private Integer pageSize;
	
	public String list(){
		//封装离线查询对象
		try{
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		if(StringUtils.isNotBlank(linkman.getLkm_name())){
			dc.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		}
		
		//1
		PageBean pb = lms.getPageBean(dc,currentPage,pageSize);
		//2
		ActionContext.getContext().put("pageBean", pb);
		ActionContext.getContext().put("lkm_name", linkman.getLkm_name());
		}catch(Exception e){
			System.out.println(e);
		}
		return "list";
	}
	


	public String add() throws Exception {
		lms.save(linkman);
		return "toList";
	}
	
	public void addUI() throws Exception {
		//强制使Linkman的增加选项通过Action拦截器
		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getServletContext().getContextPath()+"/jsp/linkman/add.jsp");
		return;
	}
	
	public String edit() throws Exception {
		LinkMan lkm = lms.getById(linkman.getLkm_id());
		ActionContext.getContext().put("linkman", lkm);
		return "toEdit";
	}

	@Override
	public LinkMan getModel() {
		return linkman;
	}


	public void setLms(LinkManService lms) {
		this.lms = lms;
	}



	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}



	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}




	public Integer getCurrentPage() {
		return currentPage;
	}



	public Integer getPageSize() {
		return pageSize;
	}


}
