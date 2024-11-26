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
    <link rel="stylesheet"
               type="text/css" href="<@spring.url '/css/style.css'/>"/>
</head>
<body>

<br>
<form class="registration" action="registration" method="post">

    <div class="registry">
          <div class="registry_item">
             <label for="name">Name</label>
             <input id="name" type="text" name="name" placeholder="enter your name...">
          </div>
          <div class="registry_item">
             <label for="nicName">NicName</label>
             <input id="nicName" type="text" name="nicName" placeholder="enter nicName...">
          </div>
          <div class="registry_item">
             <label for="email">Email</label>
             <input id="email" type="email" name="email">
          </div>
          <div class="registry_item">
             <label for="city">City</label>
             <select id="city" name="city">
                <#list cityList as city>
                   <option value="${city.name}">${city.name}</option>
                </#list>
             </select>
          </div>
          <div class="registry_item">
             <label for="country">Country</label>
             <select id="country" name="country">
                <#list countryList as country>
                   <option value="${country.name}">${country.name}</option>
                </#list>
             </select>
          </div>
          <div class="registry_item">
             <label for="password">Password</label>
             <input id="password" type="password" name="password" placeholder="password">
          </div>
          <div class="registry_item">
             <input type="submit" name="registration" value="Registration">
          </div>
        </div>
</form>
</body>
</html>
