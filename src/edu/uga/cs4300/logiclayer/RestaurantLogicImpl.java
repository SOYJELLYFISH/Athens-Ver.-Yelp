package edu.uga.cs4300.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import Persistlayer.restaurantPersistlmpl;
import edu.uga.cs4300.objectlayer.*;
import edu.uga.cs4300.persistlayer.*;

public class RestaurantLogicImpl {
	restaurantPersistlmpl Restaurt;
	
	public RestaurantLogicImpl(){
		Restaurt = new restaurantPersistlmpl();
	}
	
	public ArrayList<ArrayList<String>> searchRestaurantByStyle(String style){
		ArrayList<ArrayList<String>> td = new ArrayList<ArrayList<String>>();
		
		ResultSet rs= Restaurt.searchRestaurantByStyle(style);
		int count = 0;
		try {
			while(rs.next()){	
				td.add(new ArrayList<String>());
				for(int i = 1;i< 6;i++){
					String element = rs.getString(i);
					td.get(count).add(element);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
	
	public ArrayList<ArrayList<String>> searchRestaurantByRating(String rating){
		ArrayList<ArrayList<String>> td = new ArrayList<ArrayList<String>>();
		
		ResultSet rs= Restaurt.searchRestaurantByRating(rating);
		int count = 0;
		try {
			while(rs.next()){	
				td.add(new ArrayList<String>());
				for(int i = 1;i< 6;i++){
					String element = rs.getString(i);
					td.get(count).add(element);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
	
	public ArrayList<ArrayList<String>> searchRestaurantByName(String name){
		ArrayList<ArrayList<String>> td = new ArrayList<ArrayList<String>>();
		
		ResultSet rs= Restaurt.searchRestaurantByName(name);
		int count = 0;
		try {
			while(rs.next()){	
				td.add(new ArrayList<String>());
				for(int i = 1;i< 6;i++){
					String element = rs.getString(i);
					td.get(count).add(element);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
	
	
	public ArrayList<ArrayList<String>> searchALLRestaurantByStyle(){
		ArrayList<ArrayList<String>> td = new ArrayList<ArrayList<String>>();
		
		ResultSet rs= Restaurt.searchALLRestaurantByStyle();
		int count = 0;
		try {
			while(rs.next()){	
				td.add(new ArrayList<String>());
				for(int i = 1;i< 6;i++){
					String element = rs.getString(i);
					td.get(count).add(element);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
	
	public ArrayList<ArrayList<String>> searchALLRestaurantByCost(){

		ArrayList<ArrayList<String>> td = new ArrayList<ArrayList<String>>();
		
		ResultSet rs= Restaurt.searchALLRestaurantByCost();
		int count = 0;
		try {
			while(rs.next()){	
				td.add(new ArrayList<String>());
				for(int i = 1;i< 6;i++){
					String element = rs.getString(i);
					td.get(count).add(element);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
	
	public ArrayList<ArrayList<String>> searchALLRestaurantByRating(){
		ArrayList<ArrayList<String>> td = new ArrayList<ArrayList<String>>();
		
		ResultSet rs= Restaurt.searchALLRestaurantByRating();
		int count = 0;
		try {
			while(rs.next()){	
				td.add(new ArrayList<String>());
				for(int i = 1;i< 6;i++){
					String element = rs.getString(i);
					td.get(count).add(element);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
	
    public int insertUser(String fname, String lname, String username, String password,String email){
    	User u = new User(username,password,fname,lname,email);
    	return Restaurt.insertUser(u);
    }
    
    public boolean isUser(String name){
    	return Restaurt.isUser(name);
    }
    
    public boolean isRestaurant(String name){
    	return Restaurt.isRestaurant(name);
    }
    
    public boolean isPasswd(String name,String passwd){
    	return Restaurt.isPasswd(name,passwd);
    }
     
    public int updateInfo(String password, String email,String fname, String lname, String username ){
    	return Restaurt.updateInfo(password,email,fname, lname, username);
    }
    
    public int addToFavorite(String restaurant_name, String username){
    	
    	return Restaurt.addToFavorite(restaurant_name,username);
    }
    
    public int deleteFavorite(String restaurant_name, String username){
    	
    	return Restaurt.deleteFavorite(restaurant_name,username);
    }
    
    /*
    *  get user favorite restaurants
    */
    public ArrayList<String> myFavorite(String username){
		ArrayList<String> td = new ArrayList<String>();
		
		ResultSet rs= Restaurt.myFavorite(username);

		try {
			while(rs.next()){	
				for(int i = 1;i< 2;i++){
					String element = rs.getString(i);
					td.add(element);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
    
    
    public ArrayList<ArrayList<String>> getMenu(String restaurant_name){
		ArrayList<ArrayList<String>> td = new ArrayList<ArrayList<String>>();
		
		ResultSet rs= Restaurt.displayMenu(restaurant_name);
		int count = 0;
		try {
			while(rs.next()){	
				td.add(new ArrayList<String>());
				for(int i = 1;i< 4;i++){
					String element = rs.getString(i);
					td.get(count).add(element);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
    
    
    public ArrayList<String> map(String restaurant_name){
		ArrayList<String> td = new ArrayList<String>();		
		ResultSet rs= Restaurt.map(restaurant_name);

		try {
			while(rs.next()){	

				for(int i = 1;i< 3;i++){
					String element = rs.getString(i);
					td.add(element);
				}
	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
    
    
    
    public ArrayList<ArrayList<String>> searchRestaurant(String sql){
		ArrayList<ArrayList<String>> td = new ArrayList<ArrayList<String>>();
		
		ResultSet rs= Restaurt.searchRestaurant(sql);
		int count = 0;
		try {
			while(rs.next()){	
				td.add(new ArrayList<String>());
				for(int i = 1;i< 6;i++){
					String element = rs.getString(i);
					td.get(count).add(element);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
    
    public ArrayList<ArrayList<String>> myFavoriteRestaurant(String sql){
		ArrayList<ArrayList<String>> td = new ArrayList<ArrayList<String>>();
		
		ResultSet rs= Restaurt.myFavoriteInfo(sql);
		int count = 0;
		try {
			while(rs.next()){	
				td.add(new ArrayList<String>());
				for(int i = 1;i< 6;i++){
					String element = rs.getString(i);
					td.get(count).add(element);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
    
    
    public ArrayList<String> userInfo(String name){
		ArrayList<String> td = new ArrayList<String>();	
		//System.out.println(name);
		ResultSet rs= Restaurt.getUserInfo(name);
		try {
			while(rs.next()){	
				for(int i = 1;i< 4;i++){
					String element = rs.getString(i);
					td.add(element);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return  td;
	}
    
    
    
    
    
    
    
    
}