<%--
  Created by IntelliJ IDEA.
  User: Yoon
  Date: 2020-02-22
  Time: 오전 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>프로젝트 정보 조회 및 변경</title>
    <style>
        ul {padding: 0;}
        li {list-style: none;}
        label {
            float: left;
            text-align: right;
            width: 60px;
        }
    </style>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h3>프로젝트 정보 조회 및 변경</h3>
<form action="update.do" method="post">
    <ul>
        <li>
            <label for="no">번호</label>
            <input id="no" type="text" name="no" value="${project.no}">
        </li>
        <li>
            <label for="title">제목</label>
            <input id="title" type="text" name="title" size="50" value="${project.title}">
        </li>
        <li>
            <label for="content">내용</label>
            <textarea id="content" name="content" rows="5" cols="40">${project.content}</textarea>
        </li>
        <li>
            <label for="sdate">시작일</label>
            <input id="sdate" type="text" name="startDate" value="${project.startDate}">
        </li>
        <li>
            <label for="edate">종료일</label>
            <input id="edate" type="text" name="endDate" value="${project.endDate}">
        </li>
        <li>
            <label for="tags">태그</label>
            <input id="tags" type="text" name="tags" size="50" value="${project.tags}">
        </li>
    </ul>
    <input type="submit" value="변경">
    <c:if test="${sessionScope.member.email eq 'admin@test.com'}">
        <input type="button" value="삭제" onClick="location.href='delete.do?no=${project.no}'">
    </c:if>
    <input type="button" value="취소"  onClick='location.href="list.do"'>
</form>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
