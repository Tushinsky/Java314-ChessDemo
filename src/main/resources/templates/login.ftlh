<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url 'css/style.css'/>">
</head>
<body>
<form class="login" action="login" method="post">
    <div class="registry">
        <div class="registry_item">
            <label for="nikName">Enter nikName:</label>
            <input id="nikName" type="text" name="nikName">
        </div>
        <div class="registry_item">
            <label for="password">Enter password:</label>
            <input id="password" type="password" name="password">
        </div>
        <div class="registry_item">
            <input type="checkbox" name="rememberMe">REMEMBER ME
            <input type="submit" name="login" value="Login">
        </div>
    </div>
</form>

</body>
</html>