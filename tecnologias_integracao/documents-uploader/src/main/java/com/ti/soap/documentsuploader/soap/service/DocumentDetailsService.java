package com.ti.soap.documentsuploader.soap.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ti.soap.documentsuploader.bean.Document;

@Component
public class DocumentDetailsService {
	
	public enum Status {
		SUCCESS, FAILURE;
	}
	
	private static List<Document> documents = new ArrayList<>();
	
	static {
		Document document1 = new Document(1, "Spring", "10 Steps", "10 Steps", "10 Steps", 3, 4, 5);
		documents.add(document1);
		Document document2 = new Document(2, "Spring MVC", "10 Examples", "10 Examples", "10 Examples", 8, 6, 5);
		documents.add(document2);
		Document document3 = new Document(3, "Spring Boot", "6k Students", "6k Students", "6k Students", 12, 756, 3);
		documents.add(document3);
		Document document4 = new Document(4, "Maven", "Most popular maven document in Udemy", "6k Students", "6k Students", 234, 765, 2);
		documents.add(document4);
	}
	
	// Find document by ID
	public Document findById(int id) {
		for(Document document:documents) {
			if(document.getId() == id) {
				return document;
			}
		}
		return null;
	}
	
	// Find all documents
	public List<Document> findAll() {
		return documents;
	}
	
	// Delete document by ID
	public Status deleteById(int id) {
		Iterator<Document> iterator = documents.iterator();
		while (iterator.hasNext()) {
			Document document = iterator.next();
			if(document.getId() == id) {
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}
	
	// Create document 
	public Status create(int id, String name, String author, String publisher, String location, int pages, int type_id,
			int user_id) {
		System.out.println(id);
		Document document = new Document(id, name, author, publisher, location, pages, type_id, user_id);
		boolean documentExists = false;
		
		Iterator<Document> iterator = documents.iterator();
		while (iterator.hasNext()) {
			Document documentInDB = iterator.next();
			if(documentInDB.getId() == id) {
				documentExists = true;
			}
		}
		
		if (!documentExists) {
			documents.add(document);
			return Status.SUCCESS;
		}
		else {
			return Status.FAILURE;
		}
	}
	
	// Update document by ID
	public Status update(int id, String name, String author, String publisher, String location, int pages, int type_id,
			int user_id) {
		Iterator<Document> iterator = documents.iterator();
		Document newDocument = new Document(id, name, author, publisher, location, pages, type_id, user_id);
		
		while (iterator.hasNext()) {
			Document oldDocument = iterator.next();
			if(oldDocument.getId() == id) {
				iterator.remove();
				documents.add(newDocument);
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}
}
