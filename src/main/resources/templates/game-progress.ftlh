<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Progress</title>
    <style type="text/css">
        <#include "css/style.css" />
        <#include "css/home.css" />
    </style>
</head>
<body>

<section class="one_">
    <#include "error/error.ftlh" />
    <form class="send" method="post" action="/game-progress/send">
            <h1>Шахматная партия от ${partyDate}</h1>
            <div>
                <label for="moving">Ваш ход:</label>
                <input id="moving" type="text" name="moving"/>
                <input type="submit" value="Send" />
            </div>
    </form>
    <#include "progress.ftlh" />
</section>
</body>
</html>