<%--
  Created by IntelliJ IDEA.
  User: Yoon
  Date: 2020-02-22
  Time: 오전 3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>프로젝트 목록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h3>프로젝트 목록</h3>
<p><a href="add.do">신규 프로젝트 등록</a></p>
<table style="border-collapse: collapse; width: 100%;" border="1">
    <tbody>
    <tr>
        <td style="width: 11.6667%;">번호</td>
        <td style="width: 21.6667%;">제목</td>
        <td style="width: 16.6667%;">시작일</td>
        <td style="width: 16.6667%;">종료일</td>
        <td style="width: 16.6667%;">상태</td>
        <td style="width: 16.6667%;">삭제</td>
    </tr>
    <c:forEach var="project" items="${projects}">
        <tr>
            <td style="width: 16.6667%;">${project.no}</td>
            <td style="width: 16.6667%;"><a href="update.do?no=${project.no}">${project.title}</a></td>
            <td style="width: 16.6667%;">${project.startDate}</td>
            <td style="width: 16.6667%;">${project.endDate}</td>
            <td style="width: 16.6667%;">${project.state}</td>
            <td style="width: 16.6667%;">
                <c:choose>
                    <c:when test="${sessionScope.member.email eq 'admin@test.com'}">
                        <input type="button" value="삭제" onClick="location.href='delete.do?no=${project.no}'" />
                    </c:when>
                    <c:otherwise>
                        <span>권한없음</span>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
