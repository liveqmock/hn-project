<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
<head>
<%@ include file="/common/meta.jsp" %>
<title>海南本地旅游网-后台内容管理系统</title>
</head>
<frameset rows="88,*,25" cols="*" frameborder="NO" border="0" framespacing="0" name="main">
	<frame src="/res" name="topFrame" scrolling="NO" noresize>
	<frameset cols="1,*" frameborder="no" border="0" framespacing="0" name="mainFrameset" id="mainFrameset">
		<frame src="/layouts/menu.jsp" name="leftFrame" scrolling="NO" noresize>
		<frame src="/layouts/main.jsp" name="mainFrame" noresize>
	</frameset>
	<frame src="/layouts/bottom.jsp" name="bottomFrame" scrolling="NO" noresize>
</frameset>
<noframes>
<body></body>
</noframes>
</html>