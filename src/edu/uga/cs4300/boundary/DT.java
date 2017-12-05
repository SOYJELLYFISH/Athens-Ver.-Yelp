package edu.uga.cs4300.boundary;
import edu.uga.cs4300.logiclayer.*;
import edu.uga.cs4300.objectlayer.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs4300.persistlayer.*;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;


/**
 * Servlet implementation class DT
 */
@WebServlet("/DT")
public class DT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String templateDir = "/WEB-INF/Temp";
	private TemplateProcessor processor;
    String user_perm ="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DT() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		processor = new TemplateProcessor(templateDir, getServletContext());
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("backToIndex") != null){
			response.sendRedirect("index.html");
		}
		else if(request.getParameter("logOut") != null){
			System.out.println("in");
			response.sendRedirect("index.html");
		}
		else if(request.getParameter("deleteFavorite") == null){
			deleteFavorite(request,response,user_perm);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("sign_in")!= null){
			sign_inPage(request,response);
		}
		else if(request.getParameter("Login_in") != null){
			checkUser(request,response);
		}
		else if(request.getParameter("backToLogin") != null){
			sign_inPage(request,response);
		}
		else if(request.getParameter("register_in") != null){
			register(request,response);
		}
		else if(request.getParameter("register_page") != null){
			registerPage(request,response);		
		}
		else if(request.getParameter("backToRegister") != null){
			registerPage(request,response);		
		}
		else if(request.getParameter("search_b") != null){
			search(request,response);	
		}
		else if(request.getParameter("backToWelcome") != null){
			welcomePage(request,response, user_perm );
		}
		else if(request.getParameter("sortrating") != null){
			sort(request,response);	
		}
		else if(request.getParameter("sortcost") != null){
			sort(request,response);	
		}
		else if(request.getParameter("sortstyle") != null){
			sort(request,response);	
		}
		else if(request.getParameter("info") != null){
			infoPage(request,response,user_perm);
		}
		else if(request.getParameter("update") != null){
			updateInfo(request,response,user_perm);	
		}
		else if(request.getParameter("restaurant_name") != null){
			addToFavorite(request,response,user_perm);
		}
		else if(request.getParameter("favorite") != null){
			myFavorite(request,response,user_perm);
		}
		else if(request.getParameter("Rsearch") != null){
			restaurantInfo(request,response);
		}
		else if(request.getParameter("mapButton") == null){
			map(request,response);
		}
		}
		
	
	private void sort(HttpServletRequest request, HttpServletResponse response) {	
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		RestaurantLogicImpl User = new RestaurantLogicImpl();
		ArrayList<ArrayList<String>> restaurantList = new ArrayList<ArrayList<String>>();
		
		String sort_value = "";
		
		if (request.getParameter("sortrating") != null){
			sort_value = "rating";
		}
		else if( request.getParameter("sortcost") != null){
			sort_value = "cost";
		}  
		else if (request.getParameter("sortstyle") != null){
			sort_value = "style";
		}
		
		/*
		if(sort_value.equalsIgnoreCase("style")){
			
			restaurantList = User.searchALLRestaurantByStyle();
			viewPage(request,response,restaurantList);
		}
		else if(sort_value.equalsIgnoreCase("rating")){
			
			restaurantList = User.searchALLRestaurantByRating();
			viewPage(request,response,restaurantList);
		}
		else if(sort_value.equalsIgnoreCase("cost")){
			
			restaurantList = User.searchALLRestaurantByCost();
			viewPage(request,response,restaurantList);
		}
		*/
	}
	
	
		private void checkUser(HttpServletRequest request, HttpServletResponse response) {	
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			RestaurantLogicImpl User = new RestaurantLogicImpl();
			
			if(!User.isUser(request.getParameter("username"))){
				errorPage(request,response,"username does not exist");
				return;
			}
			else if(!User.isPasswd(request.getParameter("username"),request.getParameter("passwd"))){
				errorPage(request,response,"wrong password");
				return;
			}
			else{
				welcomePage(request,response,request.getParameter("username"));
			}
	} // end of check
		
		private void register(HttpServletRequest request, HttpServletResponse response) {	
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			RestaurantLogicImpl User = new RestaurantLogicImpl();
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			
			if(lname.length() == 0 || fname.length() == 0|| username.length() == 0 || password.length() == 0){   	
	    		String errorMessage = "Form is imcomplete";    		
	    		errorPage(request,response,errorMessage);  
	    		return;
	    	}
			
			if(User.isUser(request.getParameter("username"))){
				errorPage(request,response,"username already exists");
				return;
			}
			else{
				User.insertUser(fname, lname, username, password,email);
				welcomePage(request,response,request.getParameter("username"));
			}
	} // end of check
		
		private void welcomePage(HttpServletRequest request, HttpServletResponse response,String username) {	
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			//User user = new User("tm2204","123","teng","mao");
			user_perm = username;
			String templateName = "welcomePage.ftl";
			root.put("username",username);
			processor.processTemplate(templateName, root, request, response);
			/*
			HttpSession session = request.getSession();
			synchronized(session){	
				session.setAttribute(username, user);
				
			}
			*/
	} // end of welcomePage
		

private void search(HttpServletRequest request, HttpServletResponse response) {	
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			RestaurantLogicImpl User = new RestaurantLogicImpl();
			ArrayList<ArrayList<String>> restaurantList = new ArrayList<ArrayList<String>>();
			String style = request.getParameter("style");
			String cost =  request.getParameter("cost");
			String rating = request.getParameter("rating");
			String sql = "";
			boolean ispre = false;
			if(!style.equals("All") || !cost.equals("All") || !rating.equals("All")){
				sql = "where ";
				if(!style.equals("All")){
					style = "m.style = '" + style +"' ";
					sql = sql + style; 
					ispre = true;
				} if(!cost.equals("All")){
					if(ispre){
						sql += "and ";
					}
					cost = "m.cost = '" + cost +"' ";
					sql = sql + cost; 
				} if(!rating.equals("All")){
					if(ispre){
						sql += "and ";
					}
					rating = "m.rating <= " + rating +" order by rating DESC";
					sql = sql + rating; 
				}
			}//if all	
			
			restaurantList = User.searchRestaurant(sql);
				
			ArrayList<ArrayList<ArrayList<String>>> menuList = new ArrayList<ArrayList<ArrayList<String>>>();
			ArrayList<ArrayList<String>> getMenu = new ArrayList<ArrayList<String>>();
			
			ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
			ArrayList<String> mapInfo = new ArrayList<String>();
			
			
			for(int i = 0; i < restaurantList.size(); i++){
				String restaurant_name = restaurantList.get(i).get(0);
				//System.out.println(restaurant_name);
				getMenu = User.getMenu(restaurant_name);
				mapInfo = User.map(restaurant_name);
				map.add(mapInfo);
				menuList.add(getMenu);
			}		
			
			ArrayList<String> favorite = new ArrayList<String>();
			favorite = User.myFavorite(user_perm);
			
		    viewPage(request,response,restaurantList,user_perm, menuList,map,favorite);
	} // end of search
		
	
	/*
	 * insert favorite restaurant
	 * 
	 */
	private void addToFavorite(HttpServletRequest request, HttpServletResponse response,String username) {	
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		RestaurantLogicImpl User = new RestaurantLogicImpl();
		String restaurant_name = request.getParameter("restaurant_name");
		User.addToFavorite(restaurant_name, username);
	
	} // end of addToFavorite
	
	/*
	 * delete Favorite restaurant
	 * 
	 */
	private void deleteFavorite(HttpServletRequest request, HttpServletResponse response,String username) {	
		
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		RestaurantLogicImpl User = new RestaurantLogicImpl();
		
		String [] restaurant_name = request.getParameterValues("json[]");
		
		if(restaurant_name.length != 0){
			for(int i = 0; i < restaurant_name.length;i++){			
				User.deleteFavorite(restaurant_name[i], username);			
			}
			
		}
		
		//myFavorite(request,response,username);
		
	} // end of welcomePage



/*
 * update user info 
 * 
 */
private void updateInfo(HttpServletRequest request, HttpServletResponse response,String username) {	
	DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
	SimpleHash root = new SimpleHash(db.build());
	user_perm = username;
	RestaurantLogicImpl User = new RestaurantLogicImpl();
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	String fname = request.getParameter("fname");
	String lname = request.getParameter("lname");
	User.updateInfo(password,email,fname, lname, username);
	
	String templateName = "info.ftl";
	root.put("username",username);
	root.put("email",email);
	root.put("fname",fname);
	root.put("lname",lname);
	processor.processTemplate(templateName, root, request, response);
} // end of welcomePage
		
	/*
	 * sign_inPage view	
	 */
	private void sign_inPage(HttpServletRequest request, HttpServletResponse response) {	
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "LogIn.ftl";
		processor.processTemplate(templateName, root, request, response);	
} // end of sign_inPage
	
	/*
	 * error page view
	 */
	private void errorPage(HttpServletRequest request, HttpServletResponse response,String msg) {	
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "error.ftl";
		root.put("msg",msg);
		processor.processTemplate(templateName, root, request, response);	
} // end of errorPage

	/*
	 * register page view
	 */
	private void registerPage(HttpServletRequest request, HttpServletResponse response) {	
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "register.ftl";
		processor.processTemplate(templateName, root, request, response);	
} // end of registerPage
	
	/*
	 * menu page
	 */
	private void menu(HttpServletRequest request, HttpServletResponse response,String name,ArrayList<ArrayList<String>> menuList,ArrayList<String> favorite) {	
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "menu.ftl";
		root.put("msg",name);
		root.put("isFavorite",favorite);
		root.put("username", user_perm);
		root.put("menuList", menuList);
		processor.processTemplate(templateName, root, request, response);	
} // end of registerPage
	
	/*
	 * research page view
	 */
	private void viewPage(HttpServletRequest request, HttpServletResponse response,ArrayList<ArrayList<String>> td, String username, ArrayList<ArrayList<ArrayList<String>>> menuList,ArrayList<ArrayList<String>> map,ArrayList<String> favorite) {	
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "view.ftl";	
		root.put("isFavorite", favorite);
		root.put("row",td);	
		root.put("menuList", menuList);
		root.put("username", username);
		root.put("map",map);
		processor.processTemplate(templateName, root, request, response);	
} // end of registerPage
	
	/*
	 *  info page
	 * 
	 */
	private void infoPage(HttpServletRequest request, HttpServletResponse response, String username) {	
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "info.ftl";
		RestaurantLogicImpl User = new RestaurantLogicImpl();
		ArrayList<String> userInfo = new ArrayList<String>();
		userInfo = User.userInfo(username);
		
		String email = userInfo.get(0);
		String fname = userInfo.get(1);
		String lname = userInfo.get(2);
		
		root.put("email",email);
		root.put("fname",fname);
		root.put("lname",lname);
		root.put("username", username);
		processor.processTemplate(templateName, root, request, response);	
} // end of infoPage
	
	
	private void myFavorite(HttpServletRequest request, HttpServletResponse response, String username) {	
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "favorite.ftl";
		RestaurantLogicImpl User = new RestaurantLogicImpl();
		
		ArrayList<ArrayList<String>> favoriteRestaurantList = new ArrayList<ArrayList<String>>();
		
		favoriteRestaurantList = User.myFavoriteRestaurant(username);
		
		ArrayList<ArrayList<ArrayList<String>>> menuList = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> getMenu = new ArrayList<ArrayList<String>>();
		
		ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
		ArrayList<String> mapInfo = new ArrayList<String>();
		
		for(int i = 0; i < favoriteRestaurantList.size(); i++){
			String restaurant_name = favoriteRestaurantList.get(i).get(0);
			//System.out.println(restaurant_name);
			getMenu = User.getMenu(restaurant_name);
			mapInfo = User.map(restaurant_name);
			map.add(mapInfo);
			menuList.add(getMenu);
		}		
				
		//root.put("myFavorite", favorite);
		root.put("row",favoriteRestaurantList);	
		root.put("menuList", menuList);
		root.put("username", username);
		processor.processTemplate(templateName, root, request, response);	
} // end of infoPage
	
	/*
	 * map info
	 */
	public void map(HttpServletRequest request, HttpServletResponse response){
		String restaurant_name = request.getParameter("name");	
		RestaurantLogicImpl User = new RestaurantLogicImpl();
		ArrayList<String> mapInfo = new ArrayList<String>();
		mapInfo = User.map(restaurant_name);
		String map = mapInfo.get(0) + " " + mapInfo.get(1);
		response.setContentType("text/html");
		try {
			response.getWriter().write(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//map
	
	/*
	 * check restaurant
	 */
	public void restaurantInfo(HttpServletRequest request, HttpServletResponse response){
		RestaurantLogicImpl User = new RestaurantLogicImpl();
		String restaurant_name = request.getParameter("rname");	
		
		if(!User.isRestaurant(restaurant_name)){
		String msg="Restaurant is Not in the DataBase";
		errorPage(request,response,msg);
		return;
		}
		else{
		ArrayList<ArrayList<String>> getMenu = new ArrayList<ArrayList<String>>();
		getMenu = User.getMenu(restaurant_name);
		ArrayList<String> favorite = new ArrayList<String>();
		favorite = User.myFavorite(user_perm);
		
		menu(request,response,restaurant_name,getMenu,favorite);		
		}
	}
	
	
}
