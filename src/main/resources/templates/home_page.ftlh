<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        <#include "style.css">
    </style>
</head>
<body>
    <h1>Добро пожаловать, ${name}!</h1>
    <br>
    <#if historyList??>
        <h3>Проведённые партии</h3>
        <div class="history_">

            <table>
                <tr>
                    <th width="200">Оппонент</th>
                    <th width="300">Дата проведения партии</th>
                    <th width="150">Результат</th>
                </tr>

                    <#list historyList as history>
                        <tr>
                            <td>${history.opponent}</td>
                            <td>${history.partyDate}</td>
                            <td>${history.result}</td>
                        </tr>
                    </#list>

            </table>
        </div>
    <#else>
        <h3>Ещё нет ни одной проведённой партии</h3>
    </#if>
</body>
</html>