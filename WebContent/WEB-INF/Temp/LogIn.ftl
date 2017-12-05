<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href = "register.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="topBar">
<p>DT-Diner</p>  
</div>
<div id = "content">
<table>
<tr>
    <th><div id="left">
        <form action="DT" method="post">
            <h1>Log In to DT</h1>
            <input type="text" name="username" placeholder ="Username"required/><br>
            <input type="Password" name="passwd" placeholder = "Password" required/><br>
            <input type="submit" name="Login_in" value="Login In" class = "button"  />
            </form>
            <form action="DT" method="get">
            <input type="submit" name="backToIndex" value="Home" class = "button">
        </form>
    </div>
    </th>
    <th>
    <div id="right">
    <img class="icon" src="https://static.vecteezy.com/system/resources/premium_previews/000/025/630/non_2x/restaurant-icon-vector-pack.jpg">
   </div>
    </th>
</tr>
</table>
</div>
<div class ="bottom">
<p align="center">&copy; Copyright TEAM 8</p>
<img id="logo" src="images/logo.png">
</div>
</body>
</html>