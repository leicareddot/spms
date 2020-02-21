<%--
  Created by IntelliJ IDEA.
  User: Yoon
  Date: 2020-02-13
  Time: 오전 4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>신규 회원 등록</title>
</head>
<body>
<h3>신규 회원 등록</h3>
<form action='add.do' method='post'>
    이름 : <input type='text' name='name'><br>
    이메일 : <input type='text' name='email'><br>
    비밀번호 : <input type='password' name='password'><br>
    <input type='submit' value='등록'>
    <input type='reset' value='초기화'>
</form>
</body>
</html>
