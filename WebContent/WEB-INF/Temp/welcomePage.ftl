<!DOCTYPE html>
<html>
<head>
<!-- Welcome page -->
<meta charset="UTF-8">
<title>Welcome</title>
<script src="//code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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

<div id = "content">
<form action="DT" method="post">
<!--<h1 id="error">Welcome, ${username}!</h1>-->

    <div id="hidden_menu">
    <span id = "arrow" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9654; </span>
    <p>M</p>
    <p>e</p> 
    <p>n</p> 
    <p>u</p>  
    </div>
      
    <div id="search">
    Style:<select name="style">
        <option value= "All" >All</option>
        <option value = "American">American</option>
        <option value = "Chinese">Chinese</option>
        <option value = "Japanese">Japanese</option>
        <option value = "Italian">Italian</option>
        <option value = "Mexican">Mexican</option>
        <option value = "Steak House">Steak House</option>
    </select>

    Cost:<select name="cost">
         <option value= "All" >All</option>
        <option value = "High">High</option>
        <option value = "Medium">Medium</option>
        <option value = "Low">Low</option>
    </select>

    Rating: <select name="rating">
         <option value= "All" >All</option>
         <option value = "5">5</option>
         <option value = "4">4</option>
         <option value = "3">3</option>2
         <option value = "2">2</option>
         <option value = "1">1</option>
    </select><br>
    <input type="submit" name="search_b" value ="Search" class = "button">
    </div>
    
   <div id = "rName">
   <input type="text" id = "rname" placeholder="Enter the restaurant name" name = "rname" onkeyup="check()">
   <input type="submit" id = "Rsearch" value = "Search" name="Rsearch" class="button" style="background-color: rgba(0, 0, 0, 0.1)" disabled>
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

function check(){
 var name = document.getElementById("rname");
 if(name.value.length == 0){
 	 document.getElementById("Rsearch").style.backgroundColor = "rgba(0, 0, 0, 0.1)";
 	document.getElementById("Rsearch").disabled = true;
 }
 else{
    document.getElementById("Rsearch").disabled = false;
    document.getElementById("Rsearch").style.backgroundColor = "#f51600";
 }

}

  $( function() {
    var availableTags = [
      "The Grill",
      "Transmetropolitan",
      "Shokitini",
      "Fuzzy Taco Shop",
      "Porterhouse Grill",
      "Depalma Italian Cafe",
      "The Table Bistro",
      "Sr Sol",
      "Last Resort Grill",
      "Athens Wok",
      "ADD Drug Store",
      "La Dolce Vita",
      "Utage",
      "Takorea",
      "Agua Linda Taqueria",
      "Dos Palmas Mexican Restaurant & Cantina",
      "Ted Most Best",
      "D.P.Dough",
      "Your Pie",
      "Peking Restaurant",
      "Golden Sun Chinese Restaurant",
      "Wok Star",
      "China Wok",
      "Teriyaki 101",
      "Sakura Steak House",
      "Inoko Sushi Express",
      "Bone Island Grillhouse",
      "Logan Roadhouse",
      "Outback Steakhouse",
      "Chops & Hops"      
    ];
    $("#rname").autocomplete({
      source: availableTags
    });
  } );
</script>
</body>
</html>