package com.iteason.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.domain.BaseDict;
import com.iteason.domain.Customer;
import com.iteason.service.BaseDictService;
import com.iteason.service.CustomerService;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

@Controller("baseDictAction")
@Scope("prototype")
public class BaseDictAction extends ActionSupport {
	@Resource(name="baseDictService")
	private BaseDictService baseDictService;
	private String dict_type_code;
	
	@Override
	public String execute() throws Exception {
		
		
		// 1、调用service根据typeCode获得数据字典对象的list
		List<BaseDict> list = baseDictService.getListByTypeCode(dict_type_code);
		System.out.println(dict_type_code);
		//2、将list转成json
		String json = JSONArray.fromObject(list).toString();
		//3、将json发送给浏览器
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		System.out.println(json);
		return null;//告诉struts2不需要结果处理
	}

	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	
	
		
}
