<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h1>${requestScope.title}</h1>
<div>
    <c:if test="${fn:length(requestScope.item) == 0}">
        <div>랭킹이 없습니다.</div>
    </c:if>
    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>등록시간</th>
            <c:if test="${requestScope.title eq '조회수 top 10'}"> <%--문자열 비교할때 eq 사용--%>
                <th>조회수</th>
            </c:if>
            <c:if test="${requestScope.title eq '댓글수 top 10'}">
                <th>댓글수</th>
            </c:if>
            <c:if test="${requestScope.title eq '좋아요 top 10'}">
                <th>좋아요수</th>
            </c:if>
        </tr>
        <c:forEach var="item" items="${requestScope.item}">
            <tr>
                <td>${item.iboard}</td>
                <td><c:out value="${item.title}"/></td>
                <td>${item.writerNm}</td>
                <td>${item.rdt}</td>
                <td>${item.cnt}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<script src="/res/js/board/list.js"></script>