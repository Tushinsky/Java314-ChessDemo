<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chessman List</title>
    <link rel="stylesheet"
               type="text/css" href="<@spring.url '/css/style.css'/>"/>
</head>
<body>
    <a href="<@spring.url '/admin_page'/>">Back to main page</a>
    <br/>
    <h1>User list</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>NicName</th>
            <th>Password</th>
            <th>City</th>
            <th>Country</th>
            <th>Role</th>
            <th>State</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <#list chessmanList as chessman>
        <tr>
            <td>${chessman.id}</td>
            <td>${chessman.name}</td>
            <td>${chessman.email}</td>
            <td>${chessman.nic}</td>
            <td>${chessman.password}</td>
            <td>${chessman.city}</td>
            <td>${chessman.country}</td>
            <td>${chessman.role}</td>
            <td>${chessman.state}</td>
            <td><a href="<@spring.url'/updateChessman/${chessman.id}'/>">Edit</a></td>
            <td><a href="<@spring.url'/removeChessman/${chessman.id}'/>">Delete</a></td>
        </tr>
        </#list>
    </table>
</body>
</html>
