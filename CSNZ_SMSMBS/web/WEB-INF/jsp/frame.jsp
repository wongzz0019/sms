<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
    <c:if test="${userSession.gender == 1}">
        <img class="wColck" src="../images/woman.jpg" alt=""/>
    </c:if>
    <c:if test="${userSession.gender == 2}">
        <img class="wColck" src="../images/man.jpg" alt=""/>
    </c:if>

    <div class="wFont">
        <h2>你好,${ userSession.userName }</h2>
        <h1 style="color: #24bbff;">欢迎使用好邻居超市管理系统!</h1>
        <h3 style="color: #24bbff;">你可以选择操作 订单管理、供应商管理、用户管理、密码修改或退出系统</h3>
    </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
