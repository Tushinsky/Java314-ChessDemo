<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
               type="text/css" href="<@spring.url '/style.css'/>"/>
    <title>City List</title>
</head>
<body>
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
     ${errorMessage}
    </div>
</#if>
<a href="<@spring.url '/admin_page'/>">Back to main page</a>
<br/>
<h1>City list</h1>
  <table border="1">
    <tr>
      <th width="50">ID</th>
      <th width="350">Name</th>
      <th width="60">Edit</th>
      <th width="60">Delete</th>
    </tr>
    <#list cityList as city>
      <tr>
        <td>${city.id}</td>
        <td>${city.name}</td>
        <td><a href="<@spring.url'/updateCity/${city.id}'/>">Edit</a></td>
        <td><a href="<@spring.url'/removeCity/${city.id}'/>">Delete</a></td>
      </tr>
    </#list>
  </table>

<h1>Add a City</h1>
<div>
 <fieldset>
    <legend>Add City</legend>
    <form name="add_" action="/cities/add" method="POST">
       ID: <@spring.formInput "city.id" "" "text"/>    <br/>
       Name: <@spring.formInput "city.name" "" "text"/>    <br/>
       <#if city.name??>
        <input type="submit" value="Edit" />
       <#else>
        <input type="submit" value="Add" />
       </#if>
    </form>
 </fieldset>
</div>

</body>
</html>