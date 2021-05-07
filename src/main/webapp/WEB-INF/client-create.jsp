<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'/>
    <title>Создание клиента</title>
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
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="create_client_command">
                <div id="content-header" class="my-3">
                    <div class="d-flex justify-content-between align-items-center">
                        <h4>ИНФОРМАЦИЯ О КЛИЕНТЕ</h4>
                    </div>
                </div>
                <div id="content-body" class="container">
                    <div class="input-break color-primary mb-2">
                        <p class="ml-3">Личные данные</p>
                    </div>
                    <div class="form-group">
                        <label for="surname">Фамилия*</label>
                        <input type="text" class="form-control form-control-sm" name="surname" id="surname"
                               required pattern="${regexp_user_fio}">
                    </div>
                    <div class="form-group">
                        <label for="name">Имя*</label>
                        <input type="text" class="form-control form-control-sm" name="name" id="name"
                               required pattern="${regexp_user_fio}">
                    </div>
                    <div class="form-group">
                        <label for="patronymic">Отчество*</label>
                        <input type="text" class="form-control form-control-sm" name="patronymic" id="patronymic"
                               required pattern="${regexp_user_fio}">
                    </div>
                    <div class="form-group">
                        <label for="birthdate">Дата рождения*</label>
                        <input type="date" class="form-control form-control-sm" name="birthdate" id="birthdate"
                               required max="${min_birthdate}">
                    </div>
                    <div class="my-2 d-flex">
                        <label class="mb-0 mr-4">Пол*</label>
                        <c:forEach items="${sexList}" var="sex">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="sex" id="sex_${sex.id}"
                                       value="${sex.id}" required>
                                <label class="form-check-label" for="sex_${sex.id}">${sex.name}</label>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <label for="phone_home">Телефон дом.</label>
                        <input type="tel" class="form-control form-control-sm" name="phone_home" id="phone_home"
                               pattern="${regexp_phone_number}">
                    </div>
                    <div class="form-group">
                        <label for="phone_cell">Телефон моб.</label>
                        <input type="tel" class="form-control form-control-sm" name="phone_cell" id="phone_cell"
                               pattern="${regexp_phone_number}">
                    </div>
                    <div class="form-group">
                        <label for="email">E-mail</label>
                        <input type="email" class="form-control form-control-sm" name="email" id="email"
                               aria-describedby="emailHelp"
                               pattern="${regexp_email}">
                    </div>
                    <div class="input-break color-primary mt-4 mb-2">
                        <p>Паспортные данные</p>
                    </div>
                    <div class="form-group">
                        <label for="passport_series">Серия паспорта*</label>
                        <input type="text" class="form-control form-control-sm" name="passport_series"
                               id="passport_series" required pattern="${regexp_passport_series}">
                    </div>
                    <div class="form-group">
                        <label for="passport_number">№ паспорта*</label>
                        <input type="number" class="form-control form-control-sm" name="passport_number"
                               id="passport_number" required min="1">
                    </div>
                    <div class="form-group">
                        <label for="passport_issued_by">Кем выдан*</label>
                        <input type="text" class="form-control form-control-sm" name="passport_issued_by"
                               id="passport_issued_by" required pattern="${regexp_passport_issued_by}">
                    </div>
                    <div class="form-group">
                        <label for="passport_issued_date">Дата выдачи*</label>
                        <input type="date" class="form-control form-control-sm" name="passport_issued_date"
                               id="passport_issued_date" required max="${date_today}">
                    </div>
                    <div class="form-group">
                        <label for="passport_identity_number">Идентификационный номер*</label>
                        <input type="text" class="form-control form-control-sm" name="passport_identity_number"
                               id="passport_identity_number" required pattern="${regexp_passport_identity_number}">
                    </div>
                    <div class="form-group">
                        <label for="nationality">Гражданство*</label>
                        <select class="form-control form-control-sm" name="nationality" id="nationality" required>
                            <c:forEach items="${nationalityList}" var="nationality">
                                <option value="${nationality.id}">${nationality.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="d-flex flex-column">
                        <hr/>
                        <small id="emailHelp" class="ml-5 form-text text-muted">Поля, отмеченные ' * ', обязательны для
                            заполнения.</small>
                        <button type="submit" class="btn-save-client color-primary align-self-center btn mt-3">Сохранить
                        </button>
                    </div>
                </div>
            </form>
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