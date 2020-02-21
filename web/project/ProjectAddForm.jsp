<%--
  Created by IntelliJ IDEA.
  User: Yoon
  Date: 2020-02-22
  Time: 오전 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>신규 프로젝트 등록</title>
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
<h3>신규 프로젝트 등록</h3>
<form action="add.do" method="post">
    <ul>
        <li>
            <label for="title">제목</label>
            <input id="title" type="text" name="title" size="50">
        </li>
        <li>
            <label for="content">내용</label>
            <textarea id="content" name="content" rows="5" cols="40"></textarea>
        </li>
        <li>
            <label for="sdate">시작일</label>
            <input id="sdate" type="text" name="startDate" placeholder="예) 2020-01-01">
        </li>
        <li>
            <label for="edate">종료일</label>
            <input id="edate" type="text" name="endDate" placeholder="예) 2020-01-01">
        </li>
        <li>
            <label for="tags">태그</label>
            <input id="tags" type="text" name="tags" size="50" placeholder="예) 태그1 태그2 태그3 ...">
        </li>
    </ul>
    <input type="submit" value="등록">
    <input type="reset" value="초기화">
</form>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
