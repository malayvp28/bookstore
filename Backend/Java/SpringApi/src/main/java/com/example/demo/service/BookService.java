package com.example.demo.service;
import java.util.*;
import com.example.demo.entity.*;
public interface BookService {

	public  List<Book> getbooks();
    public List<Book> getbook(String name);
    public message addbook(Book b);
    public message updatebook(Book b);
    public message deletebook(int id);
    public message deletecartbook(int id);
    public List<bill> billOforder(String s);
}
