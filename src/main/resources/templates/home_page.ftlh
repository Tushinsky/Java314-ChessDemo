<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url '../style.css'/>"/>
</head>
<body>
    <h1>Welcome, ${name}!</h1>
    <br>
    <div class="history_" style="height: 310px;overflow: auto;">
        <table border="1" style="border-collapse:collapse;">
            <tr>
                <th width="200">Opponent</th>
                <th width="300">Date of Party</th>
                <th width="150">Result</th>
            </tr>
            <#if historyList??>
                <#list historyList as history>
                    <tr>
                        <td>${history.opponent}</td>
                        <td>${history.partyDate}</td>
                        <td>${history.result}</td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</body>
</html>