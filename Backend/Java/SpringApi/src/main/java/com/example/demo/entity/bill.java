package com.example.demo.entity;

public class bill {

	  private	int id;
	  private String name;
	  private int price;
	  private String url;
	  private String author;
	  private int qnty;
	public bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public bill(int id, String name, int price, String url, String author, int qnty) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.url = url;
		this.author = author;
		this.qnty = qnty;
	}
	@Override
	public String toString() {
		return "bill [id=" + id + ", name=" + name + ", price=" + price + ", url=" + url + ", author=" + author
				+ ", qnty=" + qnty + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getQnty() {
		return qnty;
	}
	public void setQnty(int qnty) {
		this.qnty = qnty;
	}

	
	
	
}
