<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册用户</title>

<!-- 核心样式文件 -->
<link rel="stylesheet" href="assets/css/amazeui.min.css">
<script type="text/javascript" src="assets/js/jquery-1.11.3.min.js"></script>
<link rel="icon" type="image/png" href="assets/i/favicon.png">
<link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png">
<link rel="icon" type="image/png" href="assets/i/favicon.png">
<script type="text/javascript" src="assets/js/amazeui.min.js"></script>

<style>
  #vld-tooltip {
    position: absolute;
    z-index: 1000;
    padding: 5px 10px;
    background: #F37B1D;
    min-width: 150px;
    color: #fff;
    transition: all 0.15s;
    box-shadow: 0 0 5px rgba(0,0,0,.15);
    display: none;
  }

  #vld-tooltip:before {
    position: absolute;
    top: -8px;
    left: 50%;
    width: 0;
    height: 0;
    margin-left: -8px;
    content: "";
    border-width: 0 8px 8px;
    border-color: transparent transparent #F37B1D;
    border-style: none inset solid;
  }
</style>

<script type="text/javascript">
$(function() {
  var $form = $('#form-with-tooltip');
  var $tooltip = $('<div id="vld-tooltip">提示信息！</div>');
  $tooltip.appendTo(document.body);

  $form.validator();

  var validator = $form.data('amui.validator');

  $form.on('focusin focusout', '.am-form-error input', function(e) {
    if (e.type === 'focusin') {
      var $this = $(this);
      var offset = $this.offset();
      var msg = $this.data('foolishMsg') || validator.getValidationMessage($this.data('validity'));

      $tooltip.text(msg).show().css({
        left: offset.left + 10,
        top: offset.top + $(this).outerHeight() + 10
      });
    } else {
      $tooltip.hide();
    }
  });
});
</script>
</head>
<body style="background-image: url(images/timg.jpg);">
	<div style="width:550px;margin:auto;background-color: ;margin-top:150px">
	<form action="${pageContext.request.contextPath }/UserAction_regist" class="am-form" id="form-with-tooltip" method="post">
	  <fieldset>
	    <div class="am-form-group">
	      <label for="doc-vld-name-2-0">用户名：</label>
	      <input name="user_code" class="am-round"  type="text" id="doc-vld-name-2-0" minlength="3"
	             placeholder="输入用户名（至少 3 个字符）" required/>
	        <span style="color:red;">${error }</span>    
	    </div>
	
	    <div class="am-form-group ">
	      <label for="doc-vld-pwd-1-0">密码：</label>
	      <input name="user_password" class="am-round" type="password" id="doc-vld-pwd-1-0" placeholder="密码(至少3个 字符)"  minlength="3" required data-foolish-msg="至少三个字符！"/>
	    </div>
	    
	     <div class="am-form-group">
	      <label for="doc-vld-name-2-0">昵称：</label>
	      <input name="user_name" class="am-round" type="text" id="doc-vld-name-2-0" minlength="2"
	             placeholder="输入昵称（至少 2 个字符）" required/>
	    </div>
	
	    <button class="am-btn am-btn-secondary am-round" type="submit">提交</button>
	  </fieldset>
 	</form>
	</div>
</body>
</html>