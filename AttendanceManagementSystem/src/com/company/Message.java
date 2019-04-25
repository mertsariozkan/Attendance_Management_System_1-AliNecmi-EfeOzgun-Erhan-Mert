package com.company;

//Message Class which provides the core elements and details of a message.
public class Message { 
	
	private int messageId;
	private int sectionId;
	private String title;
	private String message;
	private String date;
	private String sender;
	public Message(int messageId,int sectionId,String title,String message,String date,String sender) { // Message Constructor.
		this.messageId=messageId;
		this.sectionId=sectionId;
		this.title=title;
		this.message=message;
		this.date=date;
		this.setSender(sender);
	}
	
	//Setters and Getters.
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

}
