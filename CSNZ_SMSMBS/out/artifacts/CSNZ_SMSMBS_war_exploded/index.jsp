<%--
  Created by IntelliJ IDEA.
  User: zzj
  Date: 2021/10/20/0020
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>系统登录 - 超市管理系统</title>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <script type="text/javascript">
    /* if(top.location!=self.location){
          top.location=self.location;
     } */
  </script>
</head>
<body class="login_bg">

<%--    <%--%>
<%--        Object attribute = request.getAttribute("error");--%>
<%--    %>--%>

<section class="loginBox">
  <header class="loginHeader">
    <h1>好邻居超市管理系统</h1>
  </header>
  <section class="loginCont">
    <form class="loginForm" action="${pageContext.request.contextPath}/User/login"  name="actionForm" id="actionForm"  method="post" >
<%--      				<div class="info">${msg}</div>--%>
    <div class="info" style="text-align: center;">${msg}</div>
    <div class="inputbox">
        <label for="userCode">用户名</label>
        <input type="text" class="input-text" id="userCode" name="userCode" placeholder="请输入用户名" required/>
      </div>
      <div class="inputbox">
        <label for="userPassword">密码</label>
        <input type="password" id="userPassword" name="userPassword" placeholder="请输入密码" required/>
      </div>
      <div class="subBtn">
        <input type="submit" value="登录"/>
        <input type="reset" value="重置"/>
      </div>
    </form>
  </section>
</section>
</body>
</html>
