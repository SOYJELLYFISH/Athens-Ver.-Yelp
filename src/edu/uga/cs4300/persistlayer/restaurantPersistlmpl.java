package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uga.cs4300.objectlayer.*;

public class restaurantPersistlmpl {
	
	/*
	 * search Genre
	 */
	public ResultSet searchRestaurantByStyle(String name){
		Connection con = DbAccessInterface.connect();
		String sql = "select m.name, m.style, m.cost, m.address, m.rating from restaurants m where m.style like '%"+name+"%'";
		return DbAccessInterface.retrieve(con, sql);	
	}
	/*
	 * search restaurant
	 */
	public ResultSet searchRestaurantByName(String name){
		Connection con = DbAccessInterface.connect();
		String sql = "select m.name, m.style, m.cost, m.address, m.rating from restaurants m where m.name like '%"+name+"%'";
		return DbAccessInterface.retrieve(con, sql);	
	}
	
	public ResultSet searchRestaurantByRating(String name){
		Connection con = DbAccessInterface.connect();
		String sql = "select m.name, m.style, m.cost, m.address, m.rating from restaurants m where m.rating <= " + name + " order by rating"
				+ " DESC";
		return DbAccessInterface.retrieve(con, sql);	
	}
	
	/*
	main function
	*/
	public ResultSet searchRestaurant(String command){
		Connection con = DbAccessInterface.connect();
		String sql = "select m.name, m.style, m.cost, m.address, m.rating from restaurants m " + command;
		return DbAccessInterface.retrieve(con, sql);	
	}
	
	//user info
	public ResultSet getUserInfo(String name){
		Connection con = DbAccessInterface.connect();
		String sql = "select u.email, u.fname, u.lname from user_table u where username =  '" + name + "'";
		return DbAccessInterface.retrieve(con, sql);	
	}
	
	//user info update
	
		public int updateInfo(String password, String email,String fname, String lname, String username ){
			Connection con = DbAccessInterface.connect();
			String sql = "update user_table set password = '" + password + "', email = '" + email + "', fname = '" + fname +"', lname = '" + fname + "' where username = '" + username + "'";
			return DbAccessInterface.update(con, sql);	
		}
	
	// search ALL by cost
		public ResultSet searchALLRestaurantByCost(){
			Connection con = DbAccessInterface.connect();
			String sql = "select m.name, m.style, m.cost, m.address, m.rating from restaurants m order by cost;";
			return DbAccessInterface.retrieve(con, sql);	
		}
		
		//search ALL by style
		public ResultSet searchALLRestaurantByStyle(){
			Connection con = DbAccessInterface.connect();
			String sql = "select m.name, m.style, m.cost, m.address, m.rating from restaurants m order by style;";
			return DbAccessInterface.retrieve(con, sql);	
		}
		
		//search ALL by rating
		public ResultSet searchALLRestaurantByRating(){
			Connection con = DbAccessInterface.connect();
			String sql = "select m.name, m.style, m.cost, m.address, m.rating from restaurants m order by rating DESC;";		
			return DbAccessInterface.retrieve(con, sql);	
		}

	
	/*
	 * display menu
	 */
	public ResultSet displayMenu(String name){
		Connection con = DbAccessInterface.connect();
		String sql = "select m.dish_name, m.dish_type, m.dish_price from menu m "
				+ "join restaurants r "
				+ "on r.id = m.restaurant_id "
				+ "where r.name = '" + name +"'";
		return DbAccessInterface.retrieve(con, sql);	
	}
	
	/*
	 * map info
	 */
	public ResultSet map(String name){
		Connection con = DbAccessInterface.connect();
		String sql = "select r.lat , r.lng from restaurants r "
				+ "where r.name = '" + name +"'";
		return DbAccessInterface.retrieve(con, sql);	
	}
	
	/*
	 * insert the User
	 */
	public int insertUser(User u){
		Connection con = DbAccessInterface.connect();
		String sql = "insert into user_table (fname, lname, username,password,email) values ('"+ u.getFname()+ "','" + u.getLname() +"','" + u.getUsername() + "','" + u.getPassward() + "','" + u.getEmail() +"')";	
		return DbAccessInterface.update(con,sql);
	}
	
	/*
	 * insert favorite
	 */
	public int addToFavorite(String restaurant_name, String username){
		Connection con = DbAccessInterface.connect();
		String sql = "insert into user_favorite (restaurant_id, user_id) values ((select id from restaurants where name = '" + restaurant_name+ "'),("
				+ "select id from user_table where username = '" + username +"'))";
		return DbAccessInterface.update(con,sql);
	}
	
	/*
	 * get favorite restaurant name
	 */
	public ResultSet myFavorite(String username){
		Connection con = DbAccessInterface.connect();
		String sql = "select r.name from restaurants r join user_favorite u on r.id = u.restaurant_id join user_table us on u.user_id = us.id where us.username = '"+username+"'";
		return DbAccessInterface.retrieve(con,sql);
	}
	
	
	/*
	 * get favorite restaurant info
	 * 
	 */
	
	public ResultSet myFavoriteInfo(String username){
		Connection con = DbAccessInterface.connect();
		String sql = "select r.name , r.style, r.cost, r.address, r.rating from restaurants r join user_favorite u on r.id = u.restaurant_id join user_table us on u.user_id = us.id where us.username = '"+username+"'";
		return DbAccessInterface.retrieve(con,sql);
	}
	
	/*
	 * delete Favorite
	 */
	public int deleteFavorite(String restaurant_name,String username){
		Connection con = DbAccessInterface.connect();
		String sql = "delete from user_favorite where restaurant_id = (select id from restaurants where name = '" + restaurant_name+"')"
				+ " and user_id = (select id from user_table where username = '" + username + "')";
		return DbAccessInterface.update(con,sql);
	}
	
	/*
	 * check the restaurant is in the db
	 */
	public boolean isRestaurant(String name){
		boolean found = false;
		Connection con = DbAccessInterface.connect();
		String sql = "select name from restaurants";
		ResultSet rs = DbAccessInterface.retrieve(con, sql);
		try {
			while(rs.next()){
				if(!rs.getString(1).equals(name)){
					found = false;
				}//if
				else{
					found = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessInterface.closeConnection(con);
		return found;

	}
	/*
	 * check username is valid
	*/
	public boolean isUser(String name){
		boolean found = true;
		Connection con = DbAccessInterface.connect();
		String sql = "select username from user_table";
		ResultSet rs = DbAccessInterface.retrieve(con, sql);
		try {
			while(rs.next()){
				if(!rs.getString(1).equals(name)){
					found = false;
				}//if
				else{
					found = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessInterface.closeConnection(con);
		return found;
	}
	
	/*
	 * check password is valid
	*/
	public boolean isPasswd(String name,String passwd){
		boolean found = true;
		Connection con = DbAccessInterface.connect();
		String sql = "select password from user_table where username = " + "'" + name + "'";
		ResultSet rs = DbAccessInterface.retrieve(con, sql);
		try {
			while(rs.next()){
				if(!rs.getString(1).equals(passwd)){
					found = false;
				}//if
				else{
					found = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessInterface.closeConnection(con);
		return found;
	}
	

}
