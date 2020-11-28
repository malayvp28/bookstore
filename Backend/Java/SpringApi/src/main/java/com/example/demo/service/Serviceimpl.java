package com.example.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.entity.*;


@Service
public class Serviceimpl implements BookService {

	
	//get all books ........................
	
	
	@Override
	public List<Book> getbooks() {
	List	list=new  ArrayList<>();
		Connection con=null;
		String s="";
	   	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("Driver Load");
		
		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/map_data?serverTimezone=UTC","root","");
		PreparedStatement get = con.prepareStatement("SELECT * FROM `book`");
		//System.out.println("connection established");
		ResultSet result= get.executeQuery();
	
		//System.out.println("run query");
		while(result.next())
		{
			//System.out.println("enter");
			list.add(new Book(Integer.parseInt(result.getString("book_id")),result.getString("book_name"),Integer.parseInt(result.getString("book_price")),result.getString("book_url"),result.getString("book_author")));
			
		
		
		}
	
		System.out.println("GET/getbooks");

		 
	   	}
	   	catch(ClassNotFoundException e)
	   	{
	   		System.out.println("class not found");
	   	} 
	   	catch (SQLException e)
	   	{
	   		System.out.println(e);
	   	}
	 
		return list;
	}
	
	
	
	//get book by id.......................................
	
	
	@Override
	public List<Book> getbook(String name)
	{
		
		List	list=new  ArrayList<>();
		Book temp=null;
		
		Connection con=null;
		
	   	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("Driver Load");
		
		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/map_data?serverTimezone=UTC","root","");
		PreparedStatement get = con.prepareStatement("SELECT * FROM `book` WHERE book_name like '%"+name+"%'" );
		//System.out.println("connection established");
		ResultSet result= get.executeQuery();
	
		//System.out.println("run query");
		while(result.next())
		{
			//System.out.println("enter");
			
			temp=new Book(Integer.parseInt(result.getString("book_id")),result.getString("book_name"),Integer.parseInt(result.getString("book_price")),result.getString("book_url"),result.getString("book_author"));
			list.add(temp);
		
		
		}
	
		System.out.println("GET/getbook");

		 
	   	}
	   	catch(ClassNotFoundException e)
	   	{
	   		System.out.println("class not found");
	   	} 
	   	catch (SQLException e)
	   	{
	   		System.out.println(e);
	   	}
		return list;
	}
	
	
	//addbook code...............................
	
	@Override
	public message addbook(Book b)
	{
		
		Connection con=null;
		message m;
	   	try {
	   		
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("class Loded");
	    con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/map_data?serverTimezone=UTC","root","");
	    //System.out.println("con compete");
		PreparedStatement posted = con.prepareStatement("INSERT INTO `book`(`book_name`, `book_author`, `book_price`, `book_url`) VALUES ('"+b.getName()+"','"+b.getAuthor()+"',"+b.getPrice()+",'"+b.getUrl()+"')");
		//System.out.println("connection established");
		posted.executeUpdate();
	    m=new message("Add Successfully !!",true);
		
	   	}
	   	catch(Exception e)
	   	{
	   		System.out.println(e);
	   		m=new message("Not Insert",false);
	   	} 
		System.out.println("POST/addbook");
		
		return m;
		
	}

	
	//updatebook code................................
	
	@Override
	public message updatebook(Book b)
	{
		
		Connection con=null;
		message m;
	   	try {
	   		
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("class Loded");
	    con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/map_data?serverTimezone=UTC","root","");
	   //System.out.println("con compete");
		PreparedStatement posted = con.prepareStatement("UPDATE `book` SET `book_name`='"+b.getName()+"',`book_author`='"+b.getAuthor()+"',`book_price`="+b.getPrice()+",`book_url`='"+b.getUrl()+"' WHERE `book_id`="+b.getId());
	   //System.out.println("connection established");
		posted.executeUpdate();
	    m=new message("Book Updated !!",true);
		
	   	}
	   	catch(Exception e)
	   	{
	   		System.out.println(e);
	   		m=new message("Book Not Updated !!",false);
	   	} 
		System.out.println("PUT/updatebook");
		
		return m;
		
	}
	
	
	//delete book code...................
	@Override
	public message deletebook(int id)
	{
		Connection con=null;
		message m;
	   	try {
	   		
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("class Loded");
	    con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/map_data?serverTimezone=UTC","root","");
	    //System.out.println("con compete");
		PreparedStatement posted = con.prepareStatement("DELETE FROM `book` WHERE `book_id`="+id);
		//System.out.println("connection established");
		posted.executeUpdate();
	    m=new message("Book Deleted !!",true);
		
	   	}
	   	catch(Exception e)
	   	{
	   		System.out.println(e);
	   		m=new message("Book Not Deleted !!",false);
	   	} 
		System.out.println("DELETE/deletebook");
		
		return m; 
	}
	
	@Override
	public message deletecartbook(int id)
	{
		Connection con=null;
		message m;
	   	try {
	   		
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("class Loded");
	    con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/map_data?serverTimezone=UTC","root","");
	    //System.out.println("con compete");
		PreparedStatement posted = con.prepareStatement("DELETE FROM `cart` WHERE `cid`="+id);
		//System.out.println("connection established");
		posted.executeUpdate();
	    m=new message("Book Deleted !!",true);
		
	   	}
	   	catch(Exception e)
	   	{
	   		System.out.println(e);
	   		m=new message("Book Not Deleted !!",false);
	   	} 
		System.out.println("DELETE/deletecart");
		
		return m; 
	}
	
	
	
	@Override
	public List<bill> billOforder(String s)
	{   
		List	list=new  ArrayList<>();
		Connection con=null;
		message m;
		
	   	try {
	   		
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("class Loded");
	    con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/map_data?serverTimezone=UTC","root","");
	    //System.out.println("con compete");
		PreparedStatement get= con.prepareStatement("SELECT * FROM `book` NATURAL JOIN `cart` WHERE cuser = '"+s+"'");
		//System.out.println("connection established");
	    ResultSet result= get.executeQuery();
		
		//System.out.println("run query");
		while(result.next())
		{
			//System.out.println("enter");
			list.add(new bill(Integer.parseInt(result.getString("book_id")),result.getString("book_name"),Integer.parseInt(result.getString("book_price")),result.getString("book_url"),result.getString("book_author") ,Integer.parseInt(result.getString("qnty"))));
			
		
		
		}
		PreparedStatement posted = con.prepareStatement("DELETE FROM `cart` WHERE `cuser`='"+s+"'");
		//System.out.println("connection established");
		posted.executeUpdate();
		
		
	   	}
	   	catch(Exception e)
	   	{
	   		System.out.println(e);
	   		m=new message("Not Insert",false);
	   	} 
		System.out.println("DELETE/billOforder");
		
		return list; 
	}
}
