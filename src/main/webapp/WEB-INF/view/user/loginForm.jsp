<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layer/header.jsp" %>


        <h1>로그인</h1>
        <form action="/login" method="post">
            <input type="text" name="username" value="${remember}" placeholder="Enter username"><br>
            <input type="password" name="password" placeholder="Enter username"><br>
            아이디 기억 <input type="checkbox" name="remember">
            <button type="submit">로그인</button>
        </form>

        <%@ include file="../layer/footer.jsp" %>