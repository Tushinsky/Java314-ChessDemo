<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>ChessBattle</title>
    <style>
        <#include "css/style.css">
        <#include "css/admin.css">
    </style>
</head>
<body>
<h1>Добро пожаловать в клуб любителей шахмат!</h1>
<br/>
<div class="admin_">
    <a href="<@spring.url '/registration'/>">Регистрация</a>
    <a href="<@spring.url '/login'/>">Войти</a>
    <a href="<@spring.url '/admin_page'/>">Инструменты администратора</a>
</div>
</body>
</html>