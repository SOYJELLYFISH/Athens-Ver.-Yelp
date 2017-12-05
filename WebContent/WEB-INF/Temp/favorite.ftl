<!DOCTYPE html>
<html>
<head>
<!-- Welcome page -->
<meta charset="UTF-8">

<title>My Favorite</title>

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
    		<p>M</p>
    		<p>e</p> 
    		<p>n</p> 
    		<p>u</p>   
   	 	</div>
	</form>
	<h1>My Favorite</h1>
	
	 <table id="table" align = "center">
<tr>
	<th></th><th>Name</th><th>Style</th><th>Cost</th><th>Address</th><th>Rating</th><th>Map</th>
</tr>
		<#assign index = 0>
	    <#list row as item>
			<#global name = item[0]>
			<tr class="list">
			 <th><input type = "checkbox" name = "restaurantName" value = "${item[0]}"></th>
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
	        				 </div>	
       					 </div>	  					
					</th>		
				</#list>
			
			<th><button onclick="window.document.location='#map';f1(this.id);" id = "${name}" name="mapButton" class = "mapLink">view map</button></th>		
			</tr>
		<#assign index = index +1>
		</#list>											
</table>

			<div id = "map" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>						
						<div id="mapBox">
        				</div>    				
					</div>
				</div>	
	
<form action="DT" method="get" id = "submitForm">
	<center><button name = "deleteFavorite" class = "button">Delete</button></center>
<form>
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

       var array = document.getElementsByName("restaurantName");
         var json = [];
          var index = 0;
          for(var i = 0; i < array.length;i++){
              if(array[i].checked){        
                  json[index] = array[i].value;
                  //console.log(json[index]);
                  index++;
              }
          }
     if(json.length != 0){     
	 $.ajax({
    	url:"DT",
    	type:"get",
   	 	dataType:'json',
    	data: {json:json},
    	success:function(){
          location.reload();
    },
});
   }    
    });
});
	$(document).ready(function (){	
	$('#submitMapForm').on('click', '#submitMapForm .mapLink', function(e){
	 e.preventDefault();
	 });
});


function f1(id){
	var name = id;
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