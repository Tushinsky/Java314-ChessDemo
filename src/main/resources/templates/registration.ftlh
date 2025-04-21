<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
   <style>
       <#include "css/style.css">
       <#include "css/registry.css">
       <#include "css/tooltip.css">
   </style>
</head>
<body>
<#include "error/error.ftlh">
<br>
<form class="registration" action="registration" method="post">

    <div class="registry">
          <div class="registry_item" data-tooltip="This is your name">
             <label for="name">Name</label>
             <input id="name" type="text" name="name" placeholder="enter your name...">
          </div>
          <div class="registry_item" data-tooltip="This is your nicName">
             <label for="nicName">NicName</label>
             <input id="nicName" type="text" name="nicName" placeholder="enter nicName...">
          </div>
          <div class="registry_item" data-tooltip="This is your email">
             <label for="email">Email</label>
             <input id="email" type="email" name="email">
          </div>
          <div class="registry_item" data-tooltip="This is a city">
             <label for="city">City</label>
             <select id="city" name="city">
                <#list cityList as city>
                   <option value="${city.name}">${city.name}</option>
                </#list>
             </select>
          </div>
          <div class="registry_item" data-tooltip="This is a country">
             <label for="country">Country</label>
             <select id="country" name="country">
                <#list countryList as country>
                   <option value="${country.name}">${country.name}</option>
                </#list>
             </select>
          </div>
          <div class="registry_item" data-tooltip="This is your password">
             <label for="password">Password</label>
             <input id="password" type="password" name="password" placeholder="password">
          </div>
          <div class="registry_item" data-tooltip="Push for registration">
             <input type="submit" name="registration" value="Registration">
          </div>
        </div>
</form>
</body>
</html>
