<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>
 
	function changePage(pageNumber){
		//1、获得表单
		//2、将页码的值放入对应表单隐藏域中
	 	$("#currentPageInput").val(pageNumber);
		//3、提交表单
		$("#pageForm").submit();
	}
 
	function changePageSize(pageSize){
		//alert(pageSize);
		$("#pageSizeInput").val(pageSize);
		$("#pageForm").submit();
	}
	
	function submit(){
		$("#pageForm").submit();
	}
	
	function selectCustomer(cust_id,cust_name){
		//alert(cust_name);
		//获得添加页面的window对象
		var win = window.opener;
		//获得添加页面的document对象
		var doc = win.document;
		//获得隐藏域和文本框并赋值
		doc.getElementById("cust_id").value=cust_id;
		doc.getElementById("cust_name").value=cust_name;
		
		window.close();
	}
	
	
	function deleteById(cust_id){
		//alert(cust_id);
		if(confirm("确认删除吗兄弟？")){
			window.location.href="${pageContext.request.contextPath}/CustomerAction_delete?cust_id="+cust_id;
		}
		
	}
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
										
										
										
										<FORM id="pageForm" action="${pageContext.request.contextPath}/CustomerAction_list"
												method=post>
												
												<!-- 配置隐藏域 -->
												<s:if test="#parameters.select != null">
												<input type="hidden" name="select" >
												</s:if>
												
												<input type="hidden" name="currentPage" id="currentPageInput" value="<s:property value="#pageBean.currentPage" />">
												<input type="hidden" name="pageSize" id="pageSizeInput" value="<s:property value="#pageBean.pageSize" />" />
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><INPUT class=textbox id=sChannel2
														style="WIDTH: 80px" maxLength=50 name="cust_name" value="${cust_name}"></TD>
													
													<TD><INPUT class=button id=sButton2 type="button"
														value=" 筛选 " herf="javascript:void(0)" onclick="submit()"></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>联系人</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												
												</FORM>
												<s:iterator value="#pageBean.list" var="cust">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD><s:property value="#cust.cust_name"/></TD>
													<TD><s:property value="#cust.cust_level"/></TD>
													<TD><s:property value="#cust.cust_source"/></TD>
													<TD><s:property value="#cust.cust_linkman"/></TD>
													<TD><s:property value="#cust.cust_phone"/></TD>
													<TD><s:property value="#cust.cust_mobile"/></TD>
													<TD>
													<s:if test="#parameters.select == null">
														<a href="${pageContext.request.contextPath}/CustomerAction_toEdit?cust_id=${cust.cust_id}">修改</a>
														&nbsp;&nbsp;
														<a href="javascript:void(0)" onclick="deleteById(${cust.cust_id})"/>删除</a>				
													</s:if><!-- CustomerAction_delete?cust_id=" -->
													<s:else>
														<input type="button" value="选择客户" onclick="selectCustomer(<s:property value='#cust.cust_id'/>,'<s:property value='#cust.cust_name'/>')">
													</s:else>
													
													</TD>
												</TR>
												
												</s:iterator>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="#pageBean.totalCount"/></B>]条记录,[<B><s:property value="#pageBean.totalPage"/></B>]页
												,每页显示
												<select  id="pageSizeSelect"name="pageSize"  onchange="changePageSize(this.value)">
												<option value="3" <s:property value="#pageBean.pageSize == 3?'selected':''"/> >3</option>
												<option value="5" <s:property value="#pageBean.pageSize == 5?'selected':''"/> >5</option>
												</select>
												条
												[<A href="javascript:void(0)" onclick="changePage(<s:property value="#pageBean.currentPage-1"/>)">前一页</A>]
												<B><s:property value="#pageBean.currentPage"/></B>
												[<A href="javascript:void(0)" onclick="changePage(<s:property value="#pageBean.currentPage+1"/>)">后一页</A>] 
												到
												<input type="text" size="3" id="page" name="page" />
												页
												
												<input type="button" value="Go" onclick="changePage(document.getElementById('page').value)"/>
											</DIV>
											
											
									</SPAN></TD>
								</TR>
							</TBODY>
							
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<s:debug></s:debug>
</BODY>
</HTML>
