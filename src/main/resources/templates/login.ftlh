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
    <style>
        <#include "css/style.css">
    </style>
</head>
<body>
<form class="logon" method="post" action="logon">
    <div class="registry">
        <div class="registry_item">
            <label for="name">Enter name:</label>
            <br/>
            <input id="name" type="text" name="name">
        </div>
        <br/>

        <div class="registry_item">
            <label for="nickName">Enter nickName:</label>
            <br/>
            <input id="nickName" type="text" name="nickName">
        </div>
        <br/>
        <div class="registry_item">
            <label for="password">Enter password:</label>
            <br/>
            <input id="password" type="password" name="password">
        </div>
        <br/>
        <div class="registry_item">
            <!--input type="checkbox" name="rememberMe">REMEMBER ME-->
            <br/>
            <input type="submit" value="Login"/>
        </div>
    </div>
</form>

</body>
</html>