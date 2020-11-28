package com.example.demo.entity;

public class message
{
	String message;
	boolean status;
	
	@Override
	public String toString() {
		return "message [message=" + message + ", status=" + status + "]";
	}
	public message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public message(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
