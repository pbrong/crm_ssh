<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<TITLE>${customer == null?"tianjia":"xiugai" }</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/mySelect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	/* function loadSelect(typecode,positionId,selectname,selectedId){
		//1、创建select对象，将name指定
		var $select = $("<select name="+selectname+"></select>");
		//2、添加提示选项
		$select.append($("<option value=''>---请选择---</option>"));
		//3、使用jq的ajax方法，访问后台Action
		$.post(
			"${pageContext.request.contextPath}/BaseDictAction",
			{"dict_type_code":typecode},
			function(data){
				 //jq遍历
				 $.each(data,function(i,n){
					 
					 var $option =$("<option value='"+n['dict_id']+"'>"+n["dict_item_name"]+"</option>");
					//为其选中的元素进行判断
					 if(n['dict_id'] == selectedId){
						 $option.attr("selected","selected");
					 }
					 //添加到select对象
					 $select.append($option);
				 });
			},
			"json"
		);
		
		//5、将组装好的select对象放入页面指定位置
		$("#"+positionId).append($select);
		
	}; */
	
	//联系人列表的ajax查询
	/* $(function(){
		//书写下拉选框
		var $select = $("<select name='cust_linkman'></select>");
		//选项提示
		var $option = $("<option>---请选择---</option>");
		$select.append($option);
		//alert();
		//ajax的post方式
		$.post(
		"${pageContext.request.contextPath}/CustomerAction_findLinkman",
		function(data){
			alert(data);
			//将数据遍历并放入option中
			$.each(data,function(i,n){
				//书写下拉列表
				var $option = $("<option value="+n['lkm_name']+">"+n['lkm_name']+"</option>");
				//放入下拉select中
				$select.append($option);
			});  
			
		},
		"json"
		
		);
		//将select放入特定id中
		$("#linkman").append($select);
			
	}); 
	 */
	
	//弹出选择框到联系人选择页面
	$(function(){
		 $("#choseLkm").click(function(){
			 //alert("jq");
			 var open_win = window.open('${pageContext.request.contextPath}/LinkManAction_list?select=true','','height=400px,witdh=400px');
		 });
	});
	
	$(function(){
		loadSelect("006","level","cust_level.dict_id","${customer.cust_level.dict_id}");
		loadSelect("002","source","cust_source.dict_id","${customer.cust_source.dict_id}");
		loadSelect("001","industry","cust_industry.dict_id","${customer.cust_industry.dict_id}");
	});
	
</script>

</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/CustomerAction_add"
		method=post enctype="multipart/form-data">
		
	<input type="hidden" name="cust_id" value="${customer.cust_id }">	
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
								<TD class=manageHead>当前位置：客户管理 &gt;<s:property value="#customer != null?'修改客户':'保存客户'"></s:property></TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>客户名称：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_name"
														
								value="${customer.cust_name }">
								</td>
								<td>客户级别 ：</td>
								<td id="level">
								</td>
							</TR>
							
							<TR>
								
								<td>信息来源 ：</td>
								<td id="source">
								</td>
								<td>行业性质 ：</td>
								<td id="industry">
								</td>
							</TR>
							
							
							<TR>
								
								
								<td>联系人 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2 
														style="WIDTH: 180px" maxLength=50 name="cust_linkman">
								</td>
								<td>移动电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_mobile">
								</td>
							</TR>
							
							<TR>
								<td>联系地址 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="custAddress">
								</td>
								<td>邮政编码 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="custZip">
								</td>
							</TR>
							<TR>
								<td>客户传真 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="custFax">
								</td>
								<td>客户网址 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="custWebsite">
								</td>
							</TR>
							<td>picture upload ：</td>
								<td>
								<input type="file" name="photo">
								</td>
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
	</FORM>
</BODY>
</HTML>
