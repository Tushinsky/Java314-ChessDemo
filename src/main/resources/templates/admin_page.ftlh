<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
    <link rel="stylesheet" href="<@spring.url '/style.css'/>">
</head>
<body>
    <div class="admin_">
        <table>
            <tr>
                <td><a href="<@spring.url '/cities'/>">City List</a></td>
            </tr>
            <tr>
                <td><a href="<@spring.url '/countries'/>">Country List</a></td>
            </tr>
            <tr>
                <td><a href="<@spring.url '/chessman_page'/>">User List</a></td>
            </tr>
            <tr>
                <td><a href="<@spring.url '/start_page'/>">Back to start page</a></td>
            </tr>
        </table>
    </div>
</body>
</html>