<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'/>
    <title>Отчет</title>
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

        <div id="content-wrapper" class="container-fluid mt-0 px-0">
            <div id="content" class="col-12 pt-3 pb-2 bg-light">
                <div id="content-header" class="mb-3">
                    <div class="d-flex justify-content-between align-items-center">
                        <h4>ОТЧЕТ</h4>
                    </div>
                </div>
                <div id="content-body" class="container">
                    <div class="form-group">
                        <label>Количество товара на витрине:</label>
                        <span>${report.acceptedItemsQuantity} шт.</span>
                    </div>
                    <div class="form-group">
                        <label>Сумма товара на витрине:</label>
                        <span>${report.acceptedItemsPrice} BYN</span>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <label>Из них просроченного товара:</label>
                        <span>${report.expiredItemsQuantity} шт.</span>
                    </div>
                    <div class="form-group">
                        <label>Сумма просроченного товара:</label>
                        <span>${report.expiredItemsPrice} BYN</span>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <label>Количество реализованного товара:</label>
                        <span>${report.realisedItemsQuantity} шт.</span>
                    </div>
                    <div class="form-group">
                        <label>Сумма выручки с реализованного товара:</label>
                        <span>${report.realisedItemsProfit} BYN</span>
                    </div>
                </div>
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