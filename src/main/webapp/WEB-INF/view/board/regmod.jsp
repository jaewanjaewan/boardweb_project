<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <form action="/board/regmod" method="post">
        <input type="hidden" name="iboard" value="${requestScope.item.iboard}"/> <%--requestScope.item이 빈칸으로들어감 (값이 없을때 즉 등록)--%>
        <div><label>제목 : <input type="text" name="title" value="<c:out value="${requestScope.item.title}"/>"></label></div>
        <div><label>내용 : <textarea name="ctnt"><c:out value="${requestScope.item.ctnt}"/></textarea></label></div>
        <div>
            <input type="submit" value="${title}">
            <input type="reset" value="초기화">
        </div>
    </form>
</div>
