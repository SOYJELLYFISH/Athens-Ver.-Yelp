package edu.uga.cs4300.objectlayer;

public class User {
	private String username;
	private String passward;
	private String email;
	private String fname;
	private String lname;
	
	public User(String username, String passward, String fname, String lname,String email) {
		super();
		this.username = username;
		this.passward = passward;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassward() {
		return passward;
	}
	public void setPassward(String passward) {
		this.passward = passward;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
}
