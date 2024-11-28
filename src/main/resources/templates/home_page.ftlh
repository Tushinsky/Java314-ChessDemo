<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/style.css'/>"/>
</head>
<body>
    <h1>Welcome, ${name}</h1>
    <br>

</body>
</html>