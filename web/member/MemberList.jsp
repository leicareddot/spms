<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원 목록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h3>회원 목록</h3>
번호,
<c:choose>
    <c:when test="${orderCond == 'NAME_ASC'}">
        <a href="list.do?orderCond=NAME_DESC">이름↑</a>
    </c:when>
    <c:when test="${orderCond == 'NAME_DESC'}">
        <a href="list.do?orderCond=NAME_ASC">이름↓</a>
    </c:when>
    <c:otherwise>
        <a href="list.do?orderCond=NAME_ASC">이름</a>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${orderCond == 'EMAIL_ASC'}">
        <a href="list.do?orderCond=EMAIL_DESC">이메일↑</a>
    </c:when>
    <c:when test="${orderCond == 'EMAIL_DESC'}">
        <a href="list.do?orderCond=EMAIL_ASC">이메일↓</a>
    </c:when>
    <c:otherwise>
        <a href="list.do?orderCond=EMAIL_ASC">이메일</a>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${orderCond == 'CREATED_DATE_ASC'}">
        <a href="list.do?orderCond=CREATED_DATE_DESC">가입일↑</a>
    </c:when>
    <c:when test="${orderCond == 'CREATED_DATE_DESC'}">
        <a href="list.do?orderCond=CREATED_DATE_ASC">가입일↓</a>
    </c:when>
    <c:otherwise>
        <a href="list.do?orderCond=CREATED_DATE_ASC">가입일</a>
    </c:otherwise>
</c:choose>
<br>
<c:forEach var="member" items="${members}">
    <c:if test="${sessionScope.member.email eq 'admin@test.com'}">
        <input type="button" value="삭제" onClick="location.href='delete.do?no=${member.no}'"/>
    </c:if>
    ${member.no},
    <a href="update.do?no=${member.no}">${member.name}</a>,
    ${member.email},
    ${member.createdDate}<br>
</c:forEach>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
