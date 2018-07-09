function loadSelect(typecode,positionId,selectname,selectedId){
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
		
	};
	