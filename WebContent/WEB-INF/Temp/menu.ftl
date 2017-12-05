<!DOCTYPE html>
<html>
<head>
<!-- menu page -->
<meta charset="UTF-8">
<title>Menu</title>
<script src="//code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>

<link href = "welcome.css" rel="stylesheet" type="text/css"/>
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

<form action="DT" method ="post" id="submitForm">
<div id = "content">

	<div id="hidden_menu">
    	<span id = "arrow" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9654; </span>
    	<p>M</p>
    	<p>e</p> 
    	<p>n</p> 
    	<p>u</p>  
 	</div>
 	
	<div id="DisplayMenu">
	<h1>${msg}</h1>
	<center><table id = "menu_table"></center>	
	    <tr id="menu_bar"><th>Dish Name</th><th>Type</th><th>Price</th></tr>
	    <#list menuList as item>
	    <tr>
	    <#list item as element>
	    <th>${element}</th>    
	    </#list>
	    </tr>
	    </#list>    
	</table>
	</div>

	<#assign x = 0>
    <#list isFavorite as bool>
    <#if bool == msg>
    <#assign x = 1>
    <#break>
    </#if>
    </#list>
    <#if x == 1>
    <button name = "restaurant_name" class = "button" value = "${msg}" style="background-color: rgba(0, 0, 0, 0.1)" disabled="disabled">Favorited</button> 
    <#assign x = 0>
    <#else>
    <button name = "restaurant_name" class = "button" value = "${msg}">Add to The Favorites </button> 
    </#if>	
 </div>   	
</form>

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

$(document).ready(function (){
    $('#submitForm').on('click', '#submitForm .button', function(e){
       e.preventDefault() //this prevents the form from submitting normally, but still allows the click to 'bubble up'.
      
       var restaurant_name = $(this).val();
      
      $(this).attr('disabled', true);
      $(this).text("Favorited");
      $(this).addClass("buttonDisable");
      
        $.ajax({
          type: "POST",
          url: "DT",
          data: {restaurant_name: restaurant_name },
          success:function(){
       		
    },
        })
    });
});

</script>
</body>
</html>
