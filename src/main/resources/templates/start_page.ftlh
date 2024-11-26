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
    <link rel="stylesheet" type="text/css" href="<@spring.url '/style.css'/>">
</head>
<body>
<h1>Welcome to ChessBattle</h1>
<br/>
<div class="admin_">
    <a href="<@spring.url '/registration'/>">Registration</a>
    <a href="<@spring.url '/login'/>">Log In</a>
    <a href="<@spring.url '/admin_page'/>">Administration</a>
</div>
</body>
</html>