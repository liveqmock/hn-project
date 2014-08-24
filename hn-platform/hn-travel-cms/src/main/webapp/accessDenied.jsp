<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Access Denied</title>
<style type="text/css">
div.error {
   	width: 260px;
   	border: 2px solid green;
   	background-color: yellow;
   	text-align: center;
}
</style>
  </head>
  <body>
    <h2>您无权访问本页面，请联系系统管理员为您分配相应访问的权限后进行！</h2>
    <hr/>
    <div class="error">访问被拒绝<br/>
      ${requestScope['SPRING_SECURITY_403_EXCEPTION'].message}
    </div>
    <hr/>
  </body>
</html>

