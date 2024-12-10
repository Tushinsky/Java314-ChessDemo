<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Заявка на игру</title>
    <style>
        <#include "css/style.css">
        <#include "css/home.css">
    </style>
</head>
<body>
<h3>Заявка на игру</h3>
<form name="saveApp" action="saveApp" method="POST">
    <div class="app_">
        <div class="app_item">
            <input type="number" name="id" value="0" hidden>
        </div>
        <div class="app_item">
            Цвет фигур:
            <select id="color" name="color">
                <option value="BLACK">BLACK</option>
                <option value="WHITE" selected>WHITE</option>
            </select>
        </div>
        <div class="app_item">
            Время на игру: <input id="time" type="time" name="time" placeholder="time..." />
        </div>
        <div class="app_item">
            Готов к игре: <input type="checkbox" name="busy" value="1" />
        </div>
        <div class="app_item">
            <input type="submit" value="Подать заявку" />
        </div>
    </div>
</form>
</body>
</html>