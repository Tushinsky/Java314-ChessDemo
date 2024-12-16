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
        <#include "css/admin.css">
        <#include "css/registry.css">
    </style>
</head>
<body>
<section>
    <#include "error/error.ftlh">
    <form class="logon" method="post" action="logon">
        <div class="registry">
            <div class="registry_item">
                <label for="name">Enter name:</label>
                <input id="name" type="text" name="name">
            </div>
            <br/>

            <div class="registry_item">
                <label for="nickName">Enter nickName:</label>
                <input id="nickName" type="text" name="nickName">
            </div>
            <br/>
            <div class="registry_item">
                <label for="password">Enter password:</label>
                <input id="password" type="password" name="password">
            </div>
            <br/>
            <div class="registry_item">
                <!--input type="checkbox" name="rememberMe">REMEMBER ME-->
                <input type="submit" value="Login"/>
            </div>
        </div>
    </form>
</section>
</body>
</html>