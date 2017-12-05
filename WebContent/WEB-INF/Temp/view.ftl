<!DOCTYPE html>
<html>
<head>
<!-- view page -->
<meta charset="UTF-8">
<title>View</title>

<script src="//code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script type="text/javascript" src="functions.js"> </script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBD6FZzZxrgLTfB3LyFxqXM45o5CmnCXZw&callback=initMap">
    </script>
<link href = "welcome.css" rel="stylesheet" type="text/css"/>

<style>
#mapBox {
    height: 400px;
    width: 100%;
    }
</style>
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
    <p onclick="openNav()">M</p>
    <p onclick="openNav()">e</p> 
    <p onclick="openNav()">n</p> 
    <p onclick="openNav()">u</p>   
    </div>
   
    <div id="search">
    Style: <select name="style">
        <option value= "All" >All</option>
        <option value = "American">American</option>
        <option value = "Chinese">Chinese</option>
        <option value = "Japanese">Japanese</option>
        <option value = "Italian">Italian</option>
        <option value = "Mexican">Mexican</option>
        <option value = "Steak House">Steak House</option>
    </select>

    Cost: <select name="cost">
         <option value= "All" >All</option>
        <option value = "High">High</option>
        <option value = "Medium">Medium</option>
        <option value = "Low">Low</option>
    </select>

    Rating: <select name="rating">
         <option value= "All" >All</option>
         <option value = "5">5</option>
         <option value = "4">4</option>
         <option value = "3">3</option>
         <option value = "2">2</option>
         <option value = "1">1</option>
    </select><br>
    <input type="submit" name="search_b" value ="Search" class = "button">
    </div>
</form>

<form action="DT" method="post" id = "submitForm">
<table id="table" align = "center">
<tr>
	<th>Name</th><th>Style</th><th>Cost</th><th>Address</th><th>Rating</th><th>Map</th>
</tr>
		<#assign index = 0>
	    <#list row as item>
			<#global name = item[0]>
			<tr class="list">
				<#list item as th>
					<th onclick="window.document.location='#${item[0]}';">
						${th}
						<div id= "${name}" class="modalDialog">
							<div>
								<a href="#close" title="Close" class="close">X</a>
								<h2>${name}</h2>	
       							<div id= "restaurant_menu">
				 					<table id = "menu_table">
	             						<tr id="menu_bar"><th>Dish Name</th><th>Type</th><th>Price</th></tr>
	            						<#list menuList[index] as list>
	            						<tr>	
	        							<#list list as menu>
	            						<th class = "food">	
	            						${menu}	
	            						</th>
	            						</#list>
	            						</tr>
	            						</#list>
	       		 					</table>
             					</div>
             					<#assign x = 0>
             					<#list isFavorite as bool>
             						<#if bool == name>
             							<#assign x = 1>
             							<#break>
             						</#if>
             					</#list>
             					<#if x == 1>
             					<button name = "restaurant_name" class = "button" value = "${name}" style="background-color: rgba(0, 0, 0, 0.1)" disabled="disabled">Favorited</button> 
             					<#assign x = 0>
             					<#else>
             					<button name = "restaurant_name" class = "button" value = "${name}">Add to The Favorites </button> 
             					</#if>		
	        				 </div>	
       					 </div>	
   					
					</th>		
				</#list>
				<th><button onclick="window.document.location='#map';f1(this.id);" id = "${name}" name="mapButton" class = "mapLink">view map</button></th>	
			</tr>
		<#assign index = index +1>
		</#list>											
</table>

</form>
</div>
</div>
				<div id = "map" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>						
						<div id="mapBox">
        				</div>    				
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

<script>

$(document).ready(function (){
    $('#submitForm').on('click', '#submitForm .button', function(e){
       e.preventDefault() //this prevents the form from submitting normally, but still allows the click to 'bubble up'.
   
       //lets get our values from the form....
      
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
	
$(document).ready(function (){	
	$('#submitForm').on('click', '#submitForm .mapLink', function(e){
	 e.preventDefault();
	 });
});


function f1(id){
	var name = id;
	console.log(id);	
	$.ajax({
   		type:"POST",
   		url:"DT",
   		data: {name: name},
     	success:
     		function(data){  		
     		initMap(data);
   			}		
   		});
   	
}


function initMap(data) {
        var lat = Number(data.substr(0,8));
        var lng = Number(data.substr(10,19));
        
        var uluru = {lat: lat, lng: lng};
        var map = new google.maps.Map(document.getElementById('mapBox'), {
          zoom: 19,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }

</script>

</body>
</html>