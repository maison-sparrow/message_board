<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>メッセージ一覧</h2>
        <ul>
        <!--DTOのMessageクラスでmessagesテーブルに各カラムを宣言して関連づけているものを
            IndexServletで全レコードを取り出し、リストのインスタンス化List<Message> messages
            それをforEachでレコードを一つずつ取り出し(var="message"という名前)でmessage.id、message.title
            のように各カラムを扱える
            message、messagesと同じ名前が何回か出てくるが、別ものも。
            ＜a href="＄｛pageContext.request.contextPath｝/new"＞この書き方ならコンテキストパスを修正しても
            JSPは修正がいらない。
            <a href="<c:url value='/new' />">でも同じ内容 -->
            <c:forEach var="message" items="${messages}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${message.id}">
                        <c:out value="${message.id}" />
                    </a>
                    ：<c:out value="${message.title}"></c:out> &gt; <c:out value="${message.content}" />
                </li>
            </c:forEach>
        </ul>
        <p><a href="${pageContext.request.contextPath}/new">新規メッセージの投稿</a></p>
    </c:param>
</c:import>