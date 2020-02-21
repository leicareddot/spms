<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원 목록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h3>회원 목록</h3>
<c:forEach var="member" items="${members}">
    <c:if test="${sessionScope.member.email == 'admin@test.com'}">
        <input type="button" value="삭제" onClick="location.href='delete.do?no=${member.no}'" />
    </c:if>
    ${member.no},
    <a href="update.do?no=${member.no}">${member.name}</a>,
    ${member.email},
    ${member.createdDate}<br>
</c:forEach>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
