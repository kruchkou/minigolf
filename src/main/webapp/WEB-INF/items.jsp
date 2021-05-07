<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'/>
    <title>Список предметов</title>
    <link rel="shortcut icon" type="image/png" href="img/title_logo.png" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/core.css">
</head>

<body>

<div id="wrapper">
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="Controller?command=go_to_all_clients_command"><img id="logo"
                                                                                         src="img/logo.png"></a>
        <p>Информационная система "ЛОМБАРД"</p>
    </nav>


    <div class="mt-4 container-fluid d-flex justify-content-center">

        <div id="left-menu" class="col-2">
            <div id="menu-header" class="d-flex justify-content-center align-items-center my-3">
                <h4>МЕНЮ</h4>
            </div>
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link color-primary" href="Controller?command=go_to_all_clients_command">
                            <img class="menu-icon" src="img/users.svg"/><span>Клиенты</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link color-secondary" href="Controller?command=go_to_create_client_command">
                            <span>Добавить клиента</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link color-primary" href="Controller?command=go_to_all_items_command">
                            <img class="menu-icon" src="img/items.svg"/><span>Товары</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link color-secondary" href="Controller?command=go_to_create_item_command">
                            <span>Добавить товар</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link color-primary" href="Controller?command=go_to_report_command">
                            <img class="menu-icon" src="img/items.svg"/><span>Отчет</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <div id="content" class="col-10 bg-light">
            <div id="content-header" class="my-3">
                <div class="d-flex align-items-center">
                    <h4>ТОВАРЫ</h4>
                </div>
            </div>

            <div id="content-body">
                <table class="table table-striped table-hover table-responsive-md">
                    <thead class="thead color-primary">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Владелец</th>
                        <th scope="col">Категория</th>
                        <th scope="col">Название</th>
                        <th scope="col">Стоимость, BYN</th>
                        <th scope="col">Дата принятия</th>
                        <th scope="col">Осталось дней</th>
                        <th scope="col">Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${itemList}" var="item">
                        <c:if test="${item.status.id == 2}">
                            <tr class="color-item-realised">
                        </c:if>
                        <c:if test="${item.status.id != 2}">
                            <c:if test="${item.daysLeft < 0}">
                                <tr class="color-item-expired">
                            </c:if>
                            <c:if test="${item.daysLeft >= 0}">
                                <tr>
                            </c:if>
                        </c:if>
                        <th scope="row">${item.id}</th>
                        <td>${item.owner.fio}</td>
                        <td>${item.category.name}</td>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                        <td>${item.acceptDate}</td>

                        <td>
                            <c:if test="${item.status.id != 2}">
                                ${item.daysLeft}
                            </c:if>
                            <c:if test="${item.status.id == 2}">
                                ${item.status.name}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.status.id != 2}">
                                <a href="Controller?command=go_to_item_command&id=${item.id}"><img
                                        src="${pageContext.request.contextPath}/img/items.svg"></a>
                                <a href="Controller?command=go_to_update_item_command&id=${item.id}"><img
                                        src="${pageContext.request.contextPath}/img/edit.svg"></a>
                                <a href="Controller?command=realise_item_command&id=${item.id}"><img
                                        src="${pageContext.request.contextPath}/img/done.svg"></a>
                                <a href="Controller?command=delete_item_command&id=${item.id}"><img
                                        src="${pageContext.request.contextPath}/img/delete.svg"></a>
                            </c:if>
                            <c:if test="${item.status.id == 2}">
                                <a href="Controller?command=go_to_item_command&id=${item.id}"><img
                                        src="${pageContext.request.contextPath}/img/items.svg"></a>
                                <a href="Controller?command=go_to_update_item_command&id=${item.id}"><img
                                        src="${pageContext.request.contextPath}/img/edit.svg"></a>
                                <a href="Controller?command=delete_item_command&id=${item.id}"><img
                                        src="${pageContext.request.contextPath}/img/delete.svg"></a>
                            </c:if>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <nav id="footer" class="navbar navbar-light bg-light justify-content-center text-muted">
            <p>Белый ломбард. Все права защищены</p>
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