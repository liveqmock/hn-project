<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>comment新增</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/comment/add" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>comment新增</small></legend>
			<!--
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" class="input-large" />
				</div>
			</div>
			-->
            <div class="control-group">
                <label class="control-label">评论对象ID:</label>
                <div class="controls">
                    <input type="text" id="objectId" name="objectId" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">评论内容:</label>
                <div class="controls">
                    <input type="text" id="content" name="content" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">属性1:</label>
                <div class="controls">
                    <input type="text" id="paramOne" name="paramOne" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">属性2:</label>
                <div class="controls">
                    <input type="text" id="paramTwo" name="paramTwo" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">属性3:</label>
                <div class="controls">
                    <input type="text" id="paramThree" name="paramThree" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">属性4:</label>
                <div class="controls">
                    <input type="text" id="paramFour" name="paramFour" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">类型:</label>
                <div class="controls">
                    <input type="text" id="commentType" name="commentType" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">用户ID:</label>
                <div class="controls">
                    <input type="text" id="userId" name="userId" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">用户名称:</label>
                <div class="controls">
                    <input type="text" id="userName" name="userName" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input type="text" id="modifyTime" name="modifyTime" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input type="text" id="createTime" name="createTime" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                    <input type="text" id="state" name="state" class="input-large" />
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
