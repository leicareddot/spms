<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원 목록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h3>회원 목록</h3>
<table style="border-collapse: collapse; width: 100%;" border="1">
    <tbody>
        <tr>
            <td>삭제</td>
            <td>번호</td>
            <td>
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
            </td>
            <td>
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
            </td>
            <td>
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
            </td>
            <c:forEach var="member" items="${members}">
        <tr>
            <td>
                <c:if test="${sessionScope.member.email eq 'admin@test.com'}">
                    <input type="button" value="삭제" onClick="location.href='delete.do?no=${member.no}'"/>
                </c:if>
            </td>
            <td>${member.no}</td>
            <td><a href="update.do?no=${member.no}">${member.name}</a></td>
            <td>${member.email}</td>
            <td>${member.createdDate}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
