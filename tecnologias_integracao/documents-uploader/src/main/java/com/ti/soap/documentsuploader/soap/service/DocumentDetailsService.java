package com.ti.soap.documentsuploader.soap.service;

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
		Document document1 = new Document(1, "Spring", "10 Steps");
		documents.add(document1);
		Document document2 = new Document(2, "Spring MVC", "10 Examples");
		documents.add(document2);
		Document document3 = new Document(3, "Spring Boot", "6k Students");
		documents.add(document3);
		Document document4 = new Document(4, "Maven", "Most popular maven document in Udemy");
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
}
