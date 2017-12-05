<!DOCTYPE html>
<html>
<head>
<!-- Welcome page -->
<meta charset="UTF-8">
<title>Info</title>
<script src="//code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script type="text/javascript" src="functions.js"> </script>

<link href = "welcome.css" rel="stylesheet" type="text/css"/>

<script>
$("#table tr").click(function(){
   $(this).addClass('selected').siblings().removeClass('selected');    
   var value=$(this).find('td:first').html();
   alert(value);    
});
</script>



</head>

<body>
    
<div id="menu" class="sidenav">

<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
<button>${username}</button><br> 
<form action="DT" method="post">
<button name= "info">Information</button><br>    
<button name="favorite">My Favorite</button><br> 
<button name ="backToWelcome">Search</button><br>     
</form> 
<form action="DT" method="get">
	<button name ="logOut">Log Out</button> 
</form> 
</div>    
    
<div id="shift">
<div id="topBar">
<p>DT-Diner</p>    
</div>    

<div id = "content">



<form action="DT" method="post">

    <div id="hidden_menu">
    <span id = "arrow" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9654; </span>
    <p>M</p>
    <p>e</p> 
    <p>n</p> 
    <p>u</p>   
    </div>
    
 
  
 <div id="search">
 <h1>Welcome ${username}</h1>  
<input type="password" name ="password" placeholder = "Password" required><br> 
<input type="text" name ="email" placeholder = "Email Address" value = "${email}" required><br>  
<input type="text" name ="fname" placeholder = "First Name"  value = "${fname}" required><br> 
<input type="text" name ="lname" placeholder = "Last Name"  value = "${lname}" required><br>
<input type ="submit" name = "update" value ="Update" class="button">
</div>



</form>

</div>





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