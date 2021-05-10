<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'/>
    <title>Обновить бронь</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/title_logo.png"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/core.css">
</head>

<body>

<div id="wrapper">
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">
            <img id="logo" src="${pageContext.request.contextPath}/img/logo.png">
        </a>
        <p>Мини-гольф</p>
    </nav>

    <div class="mt-4 container-fluid d-flex justify-content-center">
        <div id="content" class="col-10 pb-4 bg-light">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="update_order_command">
                <input type="hidden" name="id" value="${order.id}">
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
                            <label for="surname">Фамилия*</label>
                            <input type="text" class="form-control form-control-sm" name="surname" id="surname"
                                   required pattern="${regexp_user_fio}" value="${order.surname}">
                        </div>
                        <div class="form-group">
                            <label for="name">Имя*</label>
                            <input type="text" class="form-control form-control-sm" name="name" id="name"
                                   required pattern="${regexp_user_fio}" value="${order.name}">
                        </div>
                        <div class="form-group">
                            <label for="birthdate">Дата рождения*</label>
                            <input type="date" class="form-control form-control-sm" name="birthdate" id="birthdate"
                                   required max="${min_birthdate}" value="${order.birthdate}" }>
                        </div>
                        <div class="form-group">
                            <label for="phone_cell">Телефон моб.*</label>
                            <input type="tel" class="form-control form-control-sm" name="phone_cell" id="phone_cell"
                                   required
                                   pattern="${regexp_phone_number}" value="${order.phoneCell}">
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail</label>
                            <input type="email" class="form-control form-control-sm" name="email" id="email"
                                   aria-describedby="emailHelp"
                                   pattern="${regexp_email}" value="${order.email}">
                        </div>
                        <div class="input-break color-primary mb-2">
                            <p>Данные брони</p>
                        </div>

                        <div class="form-group">
                            <label for="office">Офис*</label>
                            <select class="form-control form-control-sm" name="office" id="office" required>
                                <c:forEach items="${officeList}" var="office">
                                    <c:if test="${order.office.id == office.id}">
                                        <option value="${office.id}" selected>${office.name}</option>
                                    </c:if>
                                    <c:if test="${order.office.id != office.id}">
                                        <option value="${office.id}">${office.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="payment_type">Тип оплаты*</label>
                            <select class="form-control form-control-sm" name="payment_type" id="payment_type" required>
                                <c:forEach items="${paymentTypeList}" var="paymentType">
                                    <c:if test="${order.paymentType.id == paymentType.id}">
                                        <option value="${paymentType.id} selected">${paymentType.name}</option>
                                    </c:if>
                                    <c:if test="${order.paymentType.id != paymentType.id}">
                                        <option value="${paymentType.id}">${paymentType.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="coach">Инструктор*</label>
                            <select class="form-control form-control-sm" name="coach" id="coach"
                                    required>
                                <c:forEach items="${coachList}" var="coach">
                                    <c:if test="${order.coach.id == coach.id}">
                                        <option value="${coach.id}" selected>${coach.name}</option>
                                    </c:if>
                                    <c:if test="${order.coach.id != coach.id}">
                                        <option value="${coach.id}">${coach.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="person_quantity">Количество посетилелей*</label>
                            <input type="number" class="form-control form-control-sm" name="person_quantity"
                                   id="person_quantity" value="${order.personQuantity}" required min="1" step="1">
                        </div>
                        <div class="form-group">
                            <label for="hours">Количество часов*</label>
                            <input type="number" class="form-control form-control-sm" name="hours" id="hours" value="${order.hours}"
                                   required min="1" step="1">
                        </div>
                    </div>

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