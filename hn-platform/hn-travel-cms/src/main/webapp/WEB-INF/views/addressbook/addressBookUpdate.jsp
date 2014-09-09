<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>addressBook修改</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/addressBook/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${addressBook.id }"/>
		<fieldset>
			<legend><small>addressBook修改</small></legend>
			
			<!--  
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" value="${member.userName }" class="input-large" />
				</div>
			</div>
			-->
			
            <div class="control-group">
                <label class="control-label">省编号:</label>
                <div class="controls">
                    <input type="text" id="provinceNum" name="provinceNum" value="${addressBook.provinceNum }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">市编号:</label>
                <div class="controls">
                    <input type="text" id="cityNum" name="cityNum" value="${addressBook.cityNum }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">区县编号:</label>
                <div class="controls">
                    <input type="text" id="townshipNum" name="townshipNum" value="${addressBook.townshipNum }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">地址名称:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" value="${addressBook.name }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">类型:</label>
                <div class="controls">
                    <input type="text" id="type" name="type" value="${addressBook.type }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input type="text" id="modifyTime" name="modifyTime" value="${addressBook.modifyTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input type="text" id="createTime" name="createTime" value="${addressBook.createTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                    <input type="text" id="state" name="state" value="${addressBook.state }" class="input-large" />
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
