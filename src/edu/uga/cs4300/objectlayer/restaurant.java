package edu.uga.cs4300.objectlayer;

public class restaurant {
	private String name;
	private String style;
	private String address;
	private String cost;
	private int rating;
	
	
	public restaurant(String name, String style, String address, String cost, int rating) {
		super();
		this.name = name;
		this.style = style;
		this.address = address;
		this.cost = cost;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
