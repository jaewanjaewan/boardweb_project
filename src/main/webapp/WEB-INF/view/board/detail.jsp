<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/res/css/board/detail.css">
<div>
    <h1>상세정보</h1>
     <%--내가 쓴글이면 버튼이 나타나게함--%>
    <c:if test="${sessionScope.loginUser.iuser == requestScope.item.writer}">
    <div>
        <a href="/board/del?iboard=${requestScope.item.iboard}"><button>삭제</button></a>
        <a href="/board/regmod?iboard=${requestScope.item.iboard}"><button>수정</button></a>
    </div>
    </c:if>
    <c:if test="${sessionScope.loginUser != null}">
        <div class="fav"> <%--cdn 사용--%>
            <c:choose>
                <c:when test="${requestScope.isHeart == 1}"> <%--좋아요가 눌러져있는 상태라면--%>
                    <a href="/board/heart?proc=2&iboard=${requestScope.item.iboard}"><i class="fas fa-thumbs-up"></i></a> <%--클릭하면 좋아요 삭제--%>
                </c:when>
                <c:otherwise>
                    <a href="/board/heart?proc=1&iboard=${requestScope.item.iboard}"><i class="far fa-thumbs-up"></i></a> <%--클릭하면 좋아요 등록--%>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
    <div>글번호 : ${requestScope.item.iboard}</div>
    <div>글제목 : ${requestScope.item.title}</div>
    <div>글내용 : ${requestScope.item.ctnt}</div>
    <div>조회수: ${requestScope.item.hit}</div>
    <div>작성자 : ${requestScope.item.writerNm}</div>
    <div>작성일시 : ${requestScope.item.rdt}</div>

    <c:if test="${sessionScope.loginUser != null}">
        <div>
            <form action="/board/cmt/reg" method="post">
                <input type="hidden" name="iboard" value="${requestScope.item.iboard}">
                <input type="text" name="ctnt" placeholder="댓글 내용">
                <input type="submit" value="댓글달기">
            </form>
        </div>
    </c:if>
    <div>
        <table>
            <tr>
                <th>작성자</th>
                <th>작성일</th>
                <th>내용</th>
                <th>비고</th>
            </tr>
            <c:forEach var="item" items="${requestScope.cmtList}">
                <tr>
                    <td>${item.writerNm}</td>
                    <td>${item.rdt}</td>
                    <td><c:out value="${item.ctnt}"/></td>
                    <td>
                        <c:if test="${sessionScope.loginUser.iuser == item.writer}">
                            <button onclick="openModForm(${item.icmt}, '${item.ctnt}');">수정</button>
                            <button onclick="isDelCmt(${requestScope.item.iboard}, ${item.icmt});">삭제</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%--수정 버튼을 눌렀을때 나타남 --%>
<div class="cmtModContainer">
    <div class="cmtModBody">
        <form action="/board/cmt/mod" method="post" id="cmtModFrm">
            <input type="hidden" name="iboard" value="${requestScope.item.iboard}">
            <input type="hidden" name="icmt">
            <div><input type="text" name="ctnt" placeholder="댓글 내용"></div>
            <div>
                <input type="submit" value="수정">
                <input type="button" value="취소" id="btnCancel">
            </div>
        </form>
    </div>
</div>
<script src="/res/js/board/detail.js"></script>