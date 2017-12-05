<!DOCTYPE html>
<html>
<head>
<!-- error page -->
<meta charset="UTF-8">
<title>Error</title>
<link href = "register.css" rel="stylesheet" type="text/css"/>
</head>
<body>



<div id="topBar">
	<p>DT-Diner</p>  
</div>

<div id="content">
    <form action="DT" method="post">
         <div id="error">
        <h3><img src="https://www.theonlineprinter.co.nz/img/error.png" height="30px" width="30px"> Error</h3>
        </div>
        <h1>${msg}</h1>
        <#if msg == "username does not exist">
        <input type="submit" name="backToRegister" value = "Register" class = "button">
        <input type="submit" name="backToLogin" value = "Back" class = "button">
        <#elseif msg == "wrong password">
        <input type="submit" name="backToLogin" value = "Back" class = "button">
        <#elseif msg == "Form is imcomplete">
        <input type="submit" name="backToRegister" value = "Back" class = "button">
        <#elseif msg == "username already exists">
        <input type="submit" name="backToLogin" value = "Log In" class = "button">
        <input type="submit" name="backToRegister" value = "Back" class = "button">
        <#elseif msg == "Restaurant is Not in the DataBase">
        <input type="submit" name="backToWelcome" value = "Back" class = "button">
        </#if>
    </form>
</div>
<div class ="bottom">
<p align="center">&copy; Copyright TEAM 8</p>
<img id="logo" src="images/logo.png">
</div>

<script>
var open = 0;
function openNav() {
    if(open == 0){
    document.getElementById("menu").style.width = "250px";
    document.getElementById("shift").style.marginLeft = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
    document.getElementById("arrow").innerHTML= "&#9664";
    open = 1;
    
    }
    else{
      closeNav()  
      open = 0;
    }
}

function closeNav() {
    document.getElementById("menu").style.width = "0";
    document.getElementById("shift").style.marginLeft= "0";
    document.body.style.backgroundColor = "white";
    document.getElementById("arrow").innerHTML= "&#9654";
}
</script>

</body>
</html>