package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.*;
import com.example.demo.service.BookService;


import java.util.*;

@RestController
@CrossOrigin(origins="*")

public class MyController {
    @Autowired
	private BookService ser;
    
    
    @GetMapping
	public String home()
	{
		return "welcome";
		
	}
	@GetMapping("/getbook")
	public List<Book> getbooks()
	{
	
		return this.ser.getbooks();
		
	}
	
	@GetMapping("/getbook/{name}")
	public List<Book> getbook(@PathVariable String name)
	{
		return this.ser.getbook(name);
	}
	@PostMapping("/addbook")
	
	public message addbook(@RequestBody Book b)
    {
		
		
		 return (this.ser.addbook(b));
	}
	@PutMapping("/update")
	public message update(@RequestBody Book b)
    {
		
		return (this.ser.updatebook(b));
	}
	@DeleteMapping("/delete/{id}")
	public message deletebook(@PathVariable String id)
	{
		
		return this.ser.deletebook(Integer.parseInt(id));
	}
	@DeleteMapping("/deletecart/{id}")
	public message deletecartbook(@PathVariable String id)
	{
		
		return this.ser.deletecartbook(Integer.parseInt(id));
	}
	
	@DeleteMapping("/bill/{user}")
	public List<bill> bill(@PathVariable String user)
	{
		
		return this.ser.billOforder(user);
	}
	
	
}
