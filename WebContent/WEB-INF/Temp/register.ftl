<!DOCTYPE html>
<html>
<head>
<!-- register page -->
<meta charset="UTF-8">
<title>Register</title>
<link href="register.css" rel="stylesheet" type="text/css">
</head>
<body>

<div id="topBar">
<p>DT-Diner</p>      
</div>    
    

<div id="content">
<table>
<tr><th>
    <div id="left">
    <h1>DT Register</h1>
    <form action="DT" method="post">
        <ul>
            <li><input type="text" name="username" placeholder="Username" required></li>
             <li><input type="password" name="password" placeholder="Password" required></li>
            <li><input type="text" name="fname" placeholder="First Name" required></li>
            <li><input type="text" name="lname" placeholder="Last Name" required></li>  
            <li><input type="text" name="email" placeholder="Email" required></li> 
        </ul>
        <input type="submit" name="register_in" value="Register"  class="button">
    </form>
    <form action="DT" method="get">
        <input type="submit" name="backToIndex" value="Home" class="button">
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