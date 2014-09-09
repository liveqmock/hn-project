<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>travelSelf修改</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/travelSelf/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${travelSelf.id }"/>
		<fieldset>
			<legend><small>travelSelf修改</small></legend>
			
			<!--  
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" value="${member.userName }" class="input-large" />
				</div>
			</div>
			-->
			
            <div class="control-group">
                <label class="control-label">名称:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" value="${travelSelf.name }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">市场价:</label>
                <div class="controls">
                    <input type="text" id="priceMarket" name="priceMarket" value="${travelSelf.priceMarket }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">现价:</label>
                <div class="controls">
                    <input type="text" id="priceNow" name="priceNow" value="${travelSelf.priceNow }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">支付方式:</label>
                <div class="controls">
                    <input type="text" id="payType" name="payType" value="${travelSelf.payType }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">起价说明:</label>
                <div class="controls">
                    <input type="text" id="priceDescription" name="priceDescription" value="${travelSelf.priceDescription }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">优惠活动:</label>
                <div class="controls">
                    <input type="text" id="activity" name="activity" value="${travelSelf.activity }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的省编号:</label>
                <div class="controls">
                    <input type="text" id="provinceNum" name="provinceNum" value="${travelSelf.provinceNum }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的市编号:</label>
                <div class="controls">
                    <input type="text" id="cityNum" name="cityNum" value="${travelSelf.cityNum }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的区县编号:</label>
                <div class="controls">
                    <input type="text" id="townshipNum" name="townshipNum" value="${travelSelf.townshipNum }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">简介:</label>
                <div class="controls">
                    <input type="text" id="description" name="description" value="${travelSelf.description }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">是否景点:</label>
                <div class="controls">
                    <input type="text" id="scenicFlag" name="scenicFlag" value="${travelSelf.scenicFlag }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">是否酒店:</label>
                <div class="controls">
                    <input type="text" id="hotelFlag" name="hotelFlag" value="${travelSelf.hotelFlag }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">是否机票:</label>
                <div class="controls">
                    <input type="text" id="airTicketFlag" name="airTicketFlag" value="${travelSelf.airTicketFlag }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input type="text" id="modifyTime" name="modifyTime" value="${travelSelf.modifyTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input type="text" id="createTime" name="createTime" value="${travelSelf.createTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                    <input type="text" id="state" name="state" value="${travelSelf.state }" class="input-large" />
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
