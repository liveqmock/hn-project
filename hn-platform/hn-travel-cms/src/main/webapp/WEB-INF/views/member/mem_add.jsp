<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>会员注册</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/mem/addMember" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>会员注册</small></legend>
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" class="input-large" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">手机:</label>
				<div class="controls">
					<input type="text" id="mobile" name="mobile" class="input-large" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">邮箱:</label>
				<div class="controls">
					<input type="text" id="email" name="email" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">联系地址:</label>
				<div class="controls">
					<input type="text" id="addr" name="addr" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="password" name="password" class="input-large" placeholder="...Leave it blank if no change"/>
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">确认密码:</label>
				<div class="controls">
					<input type="password" id="confirmPassword" name="confirmPassword" class="input-large" />
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#userName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
