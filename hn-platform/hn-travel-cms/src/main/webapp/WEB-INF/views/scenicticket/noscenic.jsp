<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<title>景点管理 - 门票列表</title>
<style type="text/css">
div{text-align:center}
</style>
<script type="text/javascript">
var i = 5;
$(function(){
	setTimeout(timeCount, 1000);
});
function timeCount(){
	if(i < 1){
		location.href = '${ctx}/scenic';
		return;
	}
	$('#TimeSp').text(i);
	i--;
	setTimeout(timeCount, 1000);
}
</script>
</head>

<body>
<br/><br/><br/><br/>
<div>景点不存在或已被删除！</div><br/><br/>
<div> <span id="TimeSp">5</span>秒后 <a href="${ctx}/scenic">返回景点列表</a> </div>
</body>
</html>
