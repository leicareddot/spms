<%--
  Created by IntelliJ IDEA.
  User: Yoon
  Date: 2020-02-12
  Time: 오전 3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="background-color: darkcyan; color: aliceblue; height: 65px; padding: 5px;">
    <b>SPMS</b>(Simple Project Management System)
    <span style="float: right;">
    <c:choose>
        <c:when test="${empty sessionScope.member}">
                <a href="add.do" style="color: white;">회원가입</a>&nbsp|
                <a href="../auth/login.do" style="color: white;">로그인</a>
            </span>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/member/update.do?no=${sessionScope.member.no}">${sessionScope.member.name}</a>
        <a href="../auth/logout.do" style="color: white;">로그아웃</a>
    </c:otherwise>
    </c:choose>
    </span>
    <p>MENU : <span><a href="${pageContext.request.contextPath}/project/list.do">프로젝트</a>&nbsp|</span>
        <span><a href="${pageContext.request.contextPath}/member/list.do">회원</a></span></p>
</div>