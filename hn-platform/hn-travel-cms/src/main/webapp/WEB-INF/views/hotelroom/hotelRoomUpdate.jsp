<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>hotelRoom修改</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/hotelRoom/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${hotelRoom.id }"/>
		<fieldset>
			<legend><small>hotelRoom修改</small></legend>
			
			<!--  
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" value="${member.userName }" class="input-large" />
				</div>
			</div>
			-->
			
            <div class="control-group">
                <label class="control-label">酒店ID:</label>
                <div class="controls">
                    <input type="text" id="hotelId" name="hotelId" value="${hotelRoom.hotelId }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">房型分类ID:</label>
                <div class="controls">
                    <input type="text" id="hotelRoomCategoryId" name="hotelRoomCategoryId" value="${hotelRoom.hotelRoomCategoryId }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">名称:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" value="${hotelRoom.name }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">床型:</label>
                <div class="controls">
                    <input type="text" id="bedType" name="bedType" value="${hotelRoom.bedType }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">早餐:</label>
                <div class="controls">
                    <input type="text" id="breakfast" name="breakfast" value="${hotelRoom.breakfast }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">宽带:</label>
                <div class="controls">
                    <input type="text" id="network" name="network" value="${hotelRoom.network }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">原价:</label>
                <div class="controls">
                    <input type="text" id="priceMarket" name="priceMarket" value="${hotelRoom.priceMarket }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">现价:</label>
                <div class="controls">
                    <input type="text" id="priceNow" name="priceNow" value="${hotelRoom.priceNow }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">总数量:</label>
                <div class="controls">
                    <input type="text" id="coount" name="coount" value="${hotelRoom.coount }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input type="text" id="modifyTime" name="modifyTime" value="${hotelRoom.modifyTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input type="text" id="createTime" name="createTime" value="${hotelRoom.createTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                    <input type="text" id="state" name="state" value="${hotelRoom.state }" class="input-large" />
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
