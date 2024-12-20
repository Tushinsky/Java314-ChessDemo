<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Заявка на игру</title>
    <style>
        <#include "css/style.css">
        <#include "css/gameApp.css">
    </style>
</head>
<body>
<h3>Заявка на игру</h3>
<form name="saveApp" action="saveApp" method="POST">
<#if gameApp??>
    <div class="app_">
        <div class="app_item">
            <input type="number" name="id" value="${gameApp.id}" hidden>
        </div>
        <div class="app_item">
            Цвет фигур:
            <#if gameApp.color=='BLACK'>
                <select id="color" name="color">
                    <option value="BLACK" selected>BLACK</option>
                    <option value="WHITE">WHITE</option>
                </select>
            <#else>
                <select id="color" name="color">
                    <option value="BLACK">BLACK</option>
                    <option value="WHITE" selected>WHITE</option>
                </select>
            </#if>

        </div>
        <div class="app_item">
            Время на игру: <input type="time" name="time" value="${gameApp.gameTime}" required/>
        </div>
        <div class="app_item">
            <#if gameApp.busy??>
                Готов к игре: <input type="checkbox" name="busy" value="1" checked />
            <#else>
                Готов к игре: <input type="checkbox" name="busy" value="0" />
            </#if>

        </div>
        <div class="app_item">
            <input type="submit" value="Изменить заявку" />
        </div>
    </div>
<#else>
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
            Время на игру: <input id="time" type="time" name="time" />
        </div>
        <div class="app_item">
            Готов к игре: <input type="checkbox" name="busy" value="1" />
        </div>
        <div class="app_item">
            <input type="submit" value="Подать заявку" />
        </div>
    </div>

</#if>
</form>
</body>
</html>