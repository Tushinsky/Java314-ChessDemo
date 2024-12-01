<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        <#include "style.css">
    </style>
    <title>Country List</title>
</head>
<body>
<a href="<@spring.url'/admin_page'/>">Back to main page</a>
<br/>
<h1>Country list</h1>
  <table border="1">
    <tr>
      <th width="50">ID</th>
      <th width="350">Name</th>
      <th width="90">Edit</th>
      <th width="90">Delete</th>
    </tr>
    <#list countryList as country>
      <tr>
        <td>${country.id}</td>
        <td>${country.name}</td>
        <td><a  href="<@spring.url '/updateCountry/${country.id}'/>">Edit</a></td>
        <td><a  href="<@spring.url '/removeCountry/${country.id}'/>">Delete</a></td>
      </tr>
    </#list>
  </table>

<h1>Add a Country</h1>
<div>
 <fieldset>
    <legend>Add Country</legend>
    <form name="add_" action="/countries/add" method="POST">
       ID: <@spring.formInput "country.id" "" "text"/>    <br/>
       Name: <@spring.formInput "country.name" "" "text"/>    <br/>
       <#if country.name??>
        <input type="submit" value="Edit" />
        <#else>
        <input type="submit" value="Add" />
       </#if>
    </form>
 </fieldset>
</div>

</body>
</html>