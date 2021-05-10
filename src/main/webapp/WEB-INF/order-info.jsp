<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'/>
    <title>Информация о броне</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/title_logo.png"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/core.css">
</head>

<body>

<div id="wrapper">
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}"><img id="logo"
                                                                               src="${pageContext.request.contextPath}/img/logo.png"></a>
        <p>Мини-гольф</p>
    </nav>


    <div class="mt-4 container-fluid d-flex justify-content-center">
        <div id="content" class="col-10 pb-4 bg-light">
            <div id="content-header" class="my-3">
                <div class="d-flex justify-content-between align-items-center">
                    <h4>ИНФОРМАЦИЯ О КЛИЕНТЕ</h4>
                </div>
            </div>
            <div id="content-body" class="container-fluid d-flex">
                <div class="col-12">
                    <div class="input-break color-primary mb-2">
                        <p class="ml-3">Личные данные</p>
                    </div>
                    <div class="form-group">
                        <label>Фамилия:</label>
                        <span>${order.surname}</span>
                    </div>
                    <div class="form-group">
                        <label>Имя:</label>
                        <span>${order.name}</span>
                    </div>
                    <div class="form-group">
                        <label>Дата рождения:</label>
                        <span>${order.birthdate}</span>
                    </div>
                    <div class="form-group">
                        <label>Телефон моб.</label>
                        <span>${order.phoneCell}</span>
                    </div>
                    <div class="form-group">
                        <label>E-mail</label>
                        <span>${empty client.email ? 'не указано' : client.email}</span>
                    </div>
                    <div class="input-break color-primary mb-2">
                        <p>Данные брони</p>
                    </div>
                    <div class="form-group">
                        <label>Офис:</label>
                        <span>${order.office.name}</span>
                    </div>
                    <div class="form-group">
                        <label>Тип оплаты:</label>
                        <span>${order.paymentType.name}</span>
                    </div>
                    <div class="form-group">
                        <label>Инструктор:</label>
                        <span>${order.coach.name}</span>
                    </div>
                    <div class="form-group">
                        <label>Кол-во посетителей:</label>
                        <span>${order.personQuantity}</span>
                    </div>
                    <div class="form-group">
                        <label>Кол-во часов:</label>
                        <span>${order.hours}</span>
                    </div>
                </div>
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