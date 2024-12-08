<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        <#include "css/style.css">
        <#include "css/home.css">
    </style>
</head>
<body>
    <h1>Добро пожаловать, ${chessman}!</h1>
    <br>
    <section class="one_">
        <#if historyList??>
            <h3>Проведённые партии</h3>
            <div class="history_">

                <table>
                    <tr>
                        <th width="150">Оппонент</th>
                        <th width="250">Дата проведения партии</th>
                        <th width="100">Результат</th>
                        <th width="100">Ходы</th>
                    </tr>

                        <#list historyList as history>
                            <tr>
                                <td>${history.opponent}</td>
                                <td>${history.partyDate}</td>
                                <td>${history.result}</td>
                                <td><a href="<@spring.url '/progress/?id=${history.id}'/>"
                                target="_blank">Посмотреть</a></td>
                            </tr>
                        </#list>

                </table>
            </div>
        <#else>
            <h3>Ещё нет ни одной проведённой партии</h3>
        </#if>
    </section>
    <section class="two_">
        <h3>Заявка на игру</h3>
        <form name="addApp" action="/home_page/addApp" method="POST">
            <div class="app_">
                <#if gameApp??>
                    <div class="app_item">
                        <input type="number" name="id" value="${gameApp.id}" hidden />
                    </div>
                    <div class="app_item">
                        <label for="color">Цвет фигур</label>
                        <select id="color" name="color">
                            <option value="${gameApp.color}" selected>${gameApp.color}</option>
                            <#if gameApp.color=="BLACK">
                                <option value="WHITE">WHITE</option>
                            <#else>
                                <option value="BLACK">BLACK</option>
                            </#if>
                        </select>
                    </div>
                    <div class="app_item">
                        Время на игру: <input type="time" name="time" value="${gameApp.gameTime}" />
                    </div>
                    <div class="app_item">
                        Готов к игре: <input type="checkbox" name="busy" value="${gameApp.busy}" />
                    </div>
                    <div class="app_item">
                        <input type="submit" value="Изменить" />
                    </div>
                <#else>
                    <div class="app_item">
                        <input type="number" name="id" value="0" hidden>
                    </div>
                    <div class="app_item">
                        <label for="color">Цвет фигур</label>
                        <select id="color" name="color">
                            <option value="BLACK">BLACK</option>
                            <option value="WHITE" selected>WHITE</option>
                        </select>
                    </div>
                    <div class="app_item">
                        <label for="time">Время на игру</label>
                        <input id="time" type="time" name="time" placeholder="time...">
                    </div>
                    <div class="app_item">
                        <label for="busy">Готов к игре</label>
                        <input type="checkbox" name="busy" value="1" />
                    </div>
                    <div class="app_item">
                        <input type="submit" value="Подать заявку" />
                    </div>
                </#if>
            </div>
        </form>

    </section>
</body>
</html>