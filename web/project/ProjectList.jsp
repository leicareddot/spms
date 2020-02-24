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
        <td style="width: 11.6667%;">
            <c:choose>
                <c:when test="${orderCond == 'PNO_ASC'}">
                    <a href="list.do?orderCond=PNO_DESC">번호↑</a>
                </c:when>
                <c:when test="${orderCond == 'PNO_DESC'}">
                    <a href="list.do?orderCond=PNO_ASC">번호↓</a>
                </c:when>
                <c:otherwise>
                    <a href="list.do?orderCond=PNO_ASC">번호</a>
                </c:otherwise>
            </c:choose>
        </td>
        <td style="width: 21.6667%;">
            <c:choose>
                <c:when test="${orderCond == 'TITLE_ASC'}">
                    <a href="list.do?orderCond=TITLE_DESC">제목↑</a>
                </c:when>
                <c:when test="${orderCond == 'TITLE_DESC'}">
                    <a href="list.do?orderCond=TITLE_ASC">제목↓</a>
                </c:when>
                <c:otherwise>
                    <a href="list.do?orderCond=TITLE_ASC">제목</a>
                </c:otherwise>
            </c:choose>
        </td>
        <td style="width: 16.6667%;">
            <c:choose>
                <c:when test="${orderCond == 'STARTDATE_ASC'}">
                    <a href="list.do?orderCond=STARTDATE_DESC">시작일↑</a>
                </c:when>
                <c:when test="${orderCond == 'STARTDATE_DESC'}">
                    <a href="list.do?orderCond=STARTDATE_ASC">시작일↓</a>
                </c:when>
                <c:otherwise>
                    <a href="list.do?orderCond=STARTDATE_ASC">시작일</a>
                </c:otherwise>
            </c:choose>
        </td>
        <td style="width: 16.6667%;">
            <c:choose>
                <c:when test="${orderCond == 'ENDDATE_ASC'}">
                    <a href="list.do?orderCond=ENDDATE_DESC">종료일↑</a>
                </c:when>
                <c:when test="${orderCond == 'ENDDATE_DESC'}">
                    <a href="list.do?orderCond=ENDDATE_ASC">종료일↓</a>
                </c:when>
                <c:otherwise>
                    <a href="list.do?orderCond=ENDDATE_ASC">종료일</a>
                </c:otherwise>
            </c:choose>
        </td>
        <td style="width: 16.6667%;">
            <c:choose>
                <c:when test="${orderCond == 'STATE_ASC'}">
                    <a href="list.do?orderCond=STATE_DESC">상태↑</a>
                </c:when>
                <c:when test="${orderCond == 'STATE_DESC'}">
                    <a href="list.do?orderCond=STATE_ASC">상태↓</a>
                </c:when>
                <c:otherwise>
                    <a href="list.do?orderCond=STATE_ASC">상태</a>
                </c:otherwise>
            </c:choose>
        </td>
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
