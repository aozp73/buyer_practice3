<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layer/header.jsp" %>


        <h1>상품 상세목록</h1>
        <table border="1">
            <tr>
                <th>번호</th>
                <th>이름</th>
                <th>가격</th>
                <th>재고</th>
                <th>등록일</th>
            </tr>
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.qty}</td>
                <td>${product.createdAt}</td>
            </tr>

        </table>

        <%@ include file="../layer/footer.jsp" %>