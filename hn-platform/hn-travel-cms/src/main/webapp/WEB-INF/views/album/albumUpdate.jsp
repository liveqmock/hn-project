<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>album修改</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/album/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${album.id }"/>
		<fieldset>
			<legend><small>album修改</small></legend>
			
			<!--  
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" value="${member.userName }" class="input-large" />
				</div>
			</div>
			-->
			
            <div class="control-group">
                <label class="control-label">评论对象ID:</label>
                <div class="controls">
                    <input type="text" id="objectId" name="objectId" value="${album.objectId }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">名称:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" value="${album.name }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">说明:</label>
                <div class="controls">
                    <input type="text" id="description" name="description" value="${album.description }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">原图地址:</label>
                <div class="controls">
                    <input type="text" id="originalPath" name="originalPath" value="${album.originalPath }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">大图地址:</label>
                <div class="controls">
                    <input type="text" id="largePath" name="largePath" value="${album.largePath }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">中图地址:</label>
                <div class="controls">
                    <input type="text" id="middlePath" name="middlePath" value="${album.middlePath }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">小图地址:</label>
                <div class="controls">
                    <input type="text" id="smallPath" name="smallPath" value="${album.smallPath }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">类型:</label>
                <div class="controls">
                    <input type="text" id="albumType" name="albumType" value="${album.albumType }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input type="text" id="modifyTime" name="modifyTime" value="${album.modifyTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input type="text" id="createTime" name="createTime" value="${album.createTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                    <input type="text" id="state" name="state" value="${album.state }" class="input-large" />
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
