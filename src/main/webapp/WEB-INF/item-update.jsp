<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'/>
    <title>Обновление предмета</title>
    <link rel="shortcut icon" type="image/png" href="img/title_logo.png" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/core.css">
</head>

<body>

<div id="wrapper">
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="Controller?command=go_to_all_clients_command"><img id="logo" src="img/logo.png"></a>
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

        <div id="content" class="col-10 pb-4 bg-light">
            <div id="content-header" class="my-3">
                <div class="d-flex justify-content-between align-items-center">
                    <h4>ИНФОРМАЦИЯ О ТОВАРЕ</h4>
                </div>
            </div>
            <div id="content-body" class="container">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="update_item_command">
                    <input type="hidden" name="id" value="${item.id}">
                    <div class="form-group">
                        <label for="owner">Владелец*</label>
                        <select class="form-control form-control-sm" id="owner" name="owner" required>
                            <c:forEach items="${clientList}" var="client">
                                <c:if test="${client.id == item.owner.id}">
                                    <option value="${client.id}"
                                            selected>${client.fio}</option>
                                </c:if>
                                <c:if test="${client.id != item.owner.id}">
                                    <option value="${client.id}">${client.fio}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="category">Категория товара*</label>
                        <select class="form-control form-control-sm" name="category" id="category" required>
                            <c:forEach items="${categoryList}" var="category">
                                <c:if test="${category.id == item.category.id}">
                                    <option value="${category.id}"
                                            selected>${category.name}</option>
                                </c:if>
                                <c:if test="${category.id != item.category.id}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="name">Название товара*</label>
                        <input type="text" class="form-control form-control-sm" name="name" id="name" required
                               pattern="${regexp_item_name}" value="${item.name}">
                    </div>
                    <div class="form-group">
                        <label for="description">Описание товара*</label>
                        <textarea class="form-control" name="description" id="description" rows="3" required
                                  pattern="${regexp_item_desc}">${item.desc}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="condition">Состояние товара*</label>
                        <textarea class="form-control" id="condition" name="condition" rows="3" required
                                  pattern="${regexp_item_desc}">${item.condition}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="price">Оцененная стоимость товара, BYN*</label>
                        <input type="number" class="form-control form-control-sm" id="price" name="price"
                               required min="1" value="${item.price}">
                    </div>
                    <div class="form-group">
                        <label for="acceptance-date">Дата принятия*</label>
                        <input type="date" class="form-control form-control-sm" id="acceptance-date"
                               name="acceptance-date" required max="${date_today}" value="${item.acceptDate}">
                    </div>
                    <div class="form-group">
                        <label for="keeping-days">Срок хранения, дней*</label>
                        <input type="number" class="form-control form-control-sm" name="keeping-days" id="keeping-days"
                               required min="1" value="${item.keepingDays}">
                    </div>

                    <div class="d-flex flex-column">
                        <hr/>
                        <small id="emailHelp" class="ml-5 form-text text-muted">Поля, отмеченные ' * ', обязательны для
                            заполнения.</small>
                        <button type="submit" class="btn-save-client color-primary align-self-center btn mt-3">Сохранить
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <form>

        </form>

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