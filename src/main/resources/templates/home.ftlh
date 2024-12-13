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
    <h1>Добро пожаловать, ${chessman.name}!</h1>
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
                                <td><a href="<@spring.url '/progress/${history.id}'/>"
                                target="_blank">Посмотреть</a></td>
                            </tr>
                        </#list>

                </table>
            </div>
        <#else>
            <h3>Ещё нет ни одной проведённой партии</h3>
        </#if>
    </section>
    <br />
    <section class="two_">
        <p>
            <#if !gameApp??>
                <a href="<@spring.url '/gameApp/${chessman.id}'/>">Подать заявку</a>
            <#else>
                <a href="<@spring.url '/gameApp/${chessman.id}'/>">Посмотреть заявку</a>
            </#if>
        </p>
        <br />
        <h3>Оппоненты</h3>
        <div class="opponents-list">

            <#if appList??>
                <table>
                    <tr>
                        <th width="100">Цвет фигур</th>
                        <th width="100">Время</th>
                        <th width="200">Ник</th>
                        <th width="100">Занят</th>
                        <th width="100">Вызов</th>
                    </tr>

                        <#list appList as app>
                            <tr>
                                <td>${app.color}</td>
                                <td>${app.gameTime}</td>
                                <td>${app.nic}</td>
                                <td>${app.busy?string('yes','no')}</td>
                                <td><a href="<@spring.url '/challenge/${app.id}'/>">Вызвать</a></td>
                            </tr>
                        </#list>

                </table>
            </#if>
        </div>
    </section>
</body>
</html>