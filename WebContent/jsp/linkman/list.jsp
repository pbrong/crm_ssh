<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>联系人列表</TITLE> 
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
	
	
	function choseLkm(lkm_name,lkm_id){
		//alert(lkm_name);
		//alert(lkm_id);
		//获得打开者的document对象进行dom操作
		var win_opener = window.opener;
		var opener_doc = win_opener.document;
		//dom操作
		opener_doc.getElementById("lkm_name").value=lkm_name;
		//opener_doc.getElementById("lkm_id").value=lkm_id;
		//选择完后退出窗口
		window.close();
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
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：联系人管理 &gt; 联系人列表</TD>
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
											<TBODY>
											
												<FORM id="pageForm" name="pageForm"
												action="${pageContext.request.contextPath }/LinkManAction_list"
												method=post>
												<!-- 设置隐藏域 -->
												<input type="hidden" name="currentPage" id="currentPageInput" value="<s:property value="#pageBean.currentPage" />">
												<input type="hidden" name="pageSize" id="pageSizeInput" value="<s:property value="#pageBean.pageSize" />" />
											
												<TR>
													<TD>联系人名称：</TD>
													<TD><INPUT class=textbox 
														style="WIDTH: 80px" maxLength=50 name="lkm_name" ></TD>
													
													<TD><INPUT class=button type="button"
														value=" 筛选 " name=sButton2 onclick="submit()"></TD>
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
													<TD>联系人名称</TD>
													<TD>性别</TD>
													<TD>办公电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="#pageBean.list" var="linkman">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD><s:property value="#linkman.lkm_name"/></TD>
													<TD><s:property value="#linkman.lkm_gender"/></TD>
													<TD><s:property value="#linkman.lkm_phone"/></TD>
													<TD><s:property value="#linkman.lkm_mobile"/></TD>
													
													<TD>
													<s:if test="#parameters.select == null">
															<a href="${pageContext.request.contextPath }/LinkManAction_edit?lkm_id= ${linkman.lkm_id}">修改</a>
															&nbsp;&nbsp;
															<a href="${pageContext.request.contextPath }/linkmanServlet?">删除</a>
													</s:if>
													<s:else >
															<input type="button" value="选择联系人" name="lkm_name" onclick="choseLkm('${linkman.lkm_name}',${linkman.lkm_id })">
													</s:else>
													</TD>
												</TR>
												
												</s:iterator> 
											</form>
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
</BODY>
</HTML>
