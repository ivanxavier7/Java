package com.ti.soap.documentsuploader.bean;

public class DocumentType {
	private int Id;
	private String type;
	private String format;

	public DocumentType(int id, String type, String format) {
		super();
		Id = id;
		this.type = type;
		this.format = format;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
