<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="/res/css/board/list.css">
<div>
    <form action="/board/list" class="frm" method="get" id="searchFrm">
        <div>
            <select name="searchType">
                <option value="1" ${param.searchType == 1 ? 'selected' : ''}>제목</option>
                <option value="2" ${param.searchType == 2 ? 'selected' : ''}>내용</option>
                <option value="3" ${param.searchType == 3 ? 'selected' : ''}>제목/내용</option>
                <option value="4" ${param.searchType == 4 ? 'selected' : ''}>글쓴이</option>
                <option value="5" ${param.searchType == 5 ? 'selected' : ''}>전체</option>
            </select>
            <input type="search" name="searchText" value="${param.searchText}">
            <input type="submit" value="검색">

            나타나는 행 수:
            <select name="rowCnt">
                <c:forEach var="cnt" begin="5" end="30" step="5">
                    <option value="${cnt}" ${param.rowCnt == cnt ? 'selected' : ''}>${cnt}개</option>
                </c:forEach>
            </select>
        </div>
    </form>
</div>
<c:choose>
    <c:when test="${requestScope.maxPageNum == 0}">
        <div>글이 없습니다.</div>
    </c:when>
    <c:otherwise>
        <div>
           <table id="boardTable">
               <tr>
                   <th>no</th>
                   <th>title</th>
                   <th>hits</th>
                   <th>writer</th>
                   <th>reg-datetime</th>
               </tr>
               <c:forEach items="${requestScope.list}" var="item">
                   <c:set var="eachTitle" value="${fn:replace(fn:replace(item.title, '>', '&gt'), '<', '&lt;')}"/> <%--xss 방지위해 꺽새를 치환,
                   c out으로 하면 꺽새및 마크가 그대로 출력되서 수동으로 cout을 만들어줬다. --%>
                   <c:if test="${param.searchText != null && (param.searchType == 1 || param.searchType == 3 || param.searchType == 5)}"> <%--<mark>는 색깔을 줌, jstl에서 +=는 문자열 합치기--%>
                       <c:set var="eachTitle" value="${fn:replace(eachTitle, param.searchText, '<mark>' += param.searchText += '</mark>' )}"/>
                   </c:if>
                   <c:set var="eachWriterNm" value="${fn:replace(fn:replace(item.writerNm, '>', '&gt'), '<' , '&lt;')}"/>
                   <c:if test="${param.searchText != null && (param.searchType == 4 || param.searchType == 5)}">
                       <c:set var="eachWriterNm" value="${fn:replace(eachWriterNm, param.searchText, '<mark>' += param.searchText += '</mark>' )}"/>
                   </c:if>

                   <c:set var ="pImg" value="defaultProfile.png"/>
                   <c:if test="${item.profileImg != null}">
                       <c:set var="pImg" value="profile/${item.writer}/${item.profileImg}"/>
                   </c:if>
                   <tr class="record" onclick="moveToDetail(${item.iboard});"> <%--c out쓰는이유: 자바스크립트로 인한 XSS공격 및 장난 방지, 보통 제목과 내용에씀--%>
                       <td><c:out value="${item.iboard}"/></td>
                       <td>${eachTitle}<span id="cmtcount">${item.cmtcount == 0 ? '' : [item.cmtcount]}</span></td>
                       <td><c:out value="${item.hit}"/></td>
                       <td><div class="circular--img circular--size40"><img src="/res/img/${pImg}"/></div>${eachWriterNm}</td>
                       <td><c:out value="${item.rdt}"/></td>
                   </tr>
               </c:forEach>
           </table>
        </div>
        <div class="pageContainer">
            <c:set var="selectedPage" value="${param.page == null ? 1 : param.page}"/> <%--param.page는 쿼리스트링으로 보내준 page갖고옴--%>
            <c:forEach var="page" begin="1" end="${requestScope.maxPageNum}">
                <div>
                    <a href="/board/list?page=${page}&searchType=${param.searchType}&searchText=${param.searchText}&rowCnt=${param.rowCnt}">
                        <span class="${selectedPage == page ? 'selected' : ''}">
                    <c:out value="[${page}]"/></span></a>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
<script src="/res/js/board/list.js"></script>