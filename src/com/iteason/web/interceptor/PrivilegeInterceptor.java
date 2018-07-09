package com.iteason.web.interceptor;

import com.iteason.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//获得session,获得登陆标识
		User user = (User) ActionContext.getContext().getSession().get("user");
		//判断标识是否存在
		if(user != null){
			//已登陆
			return invocation.invoke();//放行
		}else{
			//不存在
			return "toLogin";
		}
		
	}
	
}
