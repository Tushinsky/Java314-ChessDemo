<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <--meta http-equiv="Refresh" content="2"-->
</head>
<body>
    <div class="history_">
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
</body>
</html>