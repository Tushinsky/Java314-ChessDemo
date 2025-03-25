<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Progress</title>
    <style>
        <#include "css/style.css">
        <#include "css/home.css">
    </style>
</head>
<body>
<section class="one_">
    <div class="history_">
        <h1>Шахматная партия от ${partyDate}</h1>
        <div>
            <label for="name">Ваш ход:</label>
            <input id="name" type="text" name="name">
        </div>
        <table>
            <tr>
                <th>Оппонент</th>
                <th>Ходы</th>
            </tr>
            <#if progressList??>
                <#list progressList as progress>
                    <tr>
                        <td>${progress.chessManName}</td>
                        <td>${progress.moving}</td>

                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</section>
</body>
</html>