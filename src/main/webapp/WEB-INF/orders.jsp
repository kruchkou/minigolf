<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'/>
    <title>Список броней</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/title_logo.png" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/core.css">
</head>

<body>

<div id="wrapper">
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}"><img id="logo" src="${pageContext.request.contextPath}/img/logo.png"></a>
        <p>Мини-гольф</p>
    </nav>

    <div class="mt-4 container-fluid d-flex justify-content-center">
        <div id="content" class="col-10 bg-light">
            <div id="content-header" class="my-3">
                <div class="d-flex justify-content-between align-items-center">
                    <h4>СПИСОК КЛИЕНТОВ</h4>
                    <a href="Controller?command=go_to_create_order_command" class="color-primary btn"><img src="img/add.svg"></a>
                </div>
            </div>

            <div id="content-body">
                <table class="table table-striped table-hover table-responsive-lg">
                    <thead class="thead color-primary">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Клиент</th>
                        <th scope="col">Офис</th>
                        <th scope="col">Тип оплаты</th>
                        <th scope="col">Кол-во человек</th>
                        <th scope="col">Кол-во часов</th>
                        <th scope="col">Итоговая сумма</th>
                        <th scope="col">Управление</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderList}" var="order">
                        <tr>
                            <th scope="row">${order.id}</th>
                            <td>${order.surname.concat(" ").concat(order.name)}</td>
                            <td>${order.office.name}</td>
                            <td>${order.paymentType.name}</td>
                            <td>${order.personQuantity}</td>
                            <td>${order.hours}</td>
                            <td>${order.getPrice()}</td>
                            <td>
                                <a href="Controller?command=go_to_order_command&id=${order.id}"><img src="${pageContext.request.contextPath}/img/view.svg"></a>
                                <a href="Controller?command=go_to_update_order_command&id=${order.id}"><img src="${pageContext.request.contextPath}/img/edit.svg"></a>
                                <a href="Controller?command=delete_order_command&id=${order.id}"><img src="${pageContext.request.contextPath}/img/delete.svg"></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <nav id="footer" class="navbar navbar-light bg-light justify-content-center text-muted">
            <p>Мини-гольф</p>
        </nav>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
</body>

</html>