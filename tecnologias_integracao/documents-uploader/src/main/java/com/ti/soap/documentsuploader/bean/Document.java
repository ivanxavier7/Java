package com.ti.soap.documentsuploader.bean;

public class Document {
	private int Id;
	private String name;
	private String description;
	 
	public Document(int id, String name, String description) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course [Id=" + Id + ", name=" + name + ", description=" + description + "]";
	}
	 
	
}
