<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/login" method="post" id="frm">
        <div><input type="text" name="uid" placeholder="id"></div>
        <div><input type="password" name="upw" placeholder="password"></div>
        <div><input type="submit" value="login"></div>
    </form>
    <div>
        <div><input type="button" id="btnShowPw" value="비밀번호 보이기"></div>
        <a href="/user/join">join</a>
    </div>
</div>
<script src="/res/js/user/login.js"></script> <%--스크립트는 밑에--%>