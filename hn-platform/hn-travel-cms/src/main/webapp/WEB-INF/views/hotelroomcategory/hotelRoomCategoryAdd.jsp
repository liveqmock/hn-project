<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>hotelRoomCategory新增</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/hotelRoomCategory/add" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>hotelRoomCategory新增</small></legend>
			<!--
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" class="input-large" />
				</div>
			</div>
			-->
            <div class="control-group">
                <label class="control-label">酒店ID:</label>
                <div class="controls">
                    <input type="text" id="hotelId" name="hotelId" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">名称:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">面积:</label>
                <div class="controls">
                    <input type="text" id="area" name="area" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">楼层:</label>
                <div class="controls">
                    <input type="text" id="floor" name="floor" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">床型:</label>
                <div class="controls">
                    <input type="text" id="bedType" name="bedType" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">最多入住人数:</label>
                <div class="controls">
                    <input type="text" id="peopleNum" name="peopleNum" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">宽带:</label>
                <div class="controls">
                    <input type="text" id="network" name="network" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">描述:</label>
                <div class="controls">
                    <input type="text" id="description" name="description" class="input-large" />
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
