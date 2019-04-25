package com.company;

	//Admin Class which provides the core elements and details of an Admin.
	public class Admin {
	    private int id;
	    private String name;
	    private String password;

	    public Admin (int id) {
	    	this.id=id;
	    }
	    public Admin(int id, String name, String password,String department,int class_) {	//Admin Constructor.
	        this.id = id;
	        this.name = name;
	        this.password = password;
	    }

	    //Setters and Getters.
	    
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

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}