package com.ti.soap.documentsuploader.bean;

public class Document {
	private int Id;
	private String name;

	private String author;
	private String publisher;
	private String location;
	private int pages;
	private int type_id;
	private int user_id;
	
	 
	public Document(int id, String name, String author, String publisher, String location, int pages, int type_id,
			int user_id) {
		super();
		Id = id;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.location = location;
		this.pages = pages;
		this.type_id = type_id;
		this.user_id = user_id;
	}
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		return "Document [Id=" + Id + ", name=" + name + ", author=" + author + ", publisher=" + publisher
				+ ", location=" + location + ", pages=" + pages + ", type_id=" + type_id + ", user_id=" + user_id + "]";
	}
}
