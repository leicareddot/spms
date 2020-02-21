<%--
  Created by IntelliJ IDEA.
  User: Yoon
  Date: 2020-02-13
  Time: 오전 5:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원 정보</title>
</head>
<body>
<h3>회원 정보</h3>
<form action='update.do' method='post'>
    번호: <input type='text' name='no' value='${requestScope.member.no}' readonly><br>
    이름: <input type='text' name='name' value='${requestScope.member.name}'><br>
    이메일: <input type='text' name='email' value='${requestScope.member.email}'><br>
    가입일: <input type='text' name='createdDate' value='${requestScope.member.createdDate}' readonly><br>
    <input type='submit' value='수정'>
    <c:if test="${sessionScope.member.email == 'admin@test.com'}">
        <input type="button" value="삭제" onClick="location.href='delete.do?no=${requestScope.member.no}'"
           style="display: ${'admin@test.com' eq sessionScope.member.email ? inline-block : none}">
    </c:if>
    <input type='button' value='취소' onClick='location.href="list.do"'>
</form>
</body>
</html>
