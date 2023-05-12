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
		Document document1 = new Document(1, "Deep Learning With Python", "François Chollet", "Manning", "https://tanthiamhuat.files.wordpress.com/2018/03/deeplearningwithpython.pdf", 362, 1, 1);
		documents.add(document1);
		Document document2 = new Document(2, "Relatório do trabalho prático na unidade curricular de TI", "Ernest J. Kostka", "", "Pasta partilhada do grupo_TI no OneDrive", 8, 2, 2);
		documents.add(document2);
		Document document3 = new Document(3, "Segurança em Redes Informáticas", "André Zúquete", "FCA", "Na biblioteca municipal de Aveiro", 468, 3, 1);
		documents.add(document3);
		Document document4 = new Document(3, "Python Crash Course (2nd Edition) : A Hands-On, Project-Based Introduction to Programming", "Mattes, Eric", "No Starch Press", "Na biblioteca municipal de Aveiro", 544, 3, 3);
		documents.add(document4);
		Document document5 = new Document(4, "Poster do Web Summit", "", "", "No edifício da estga á frente da sala 5.1.13", 234, 4, 4);
		documents.add(document5);
		Document document6 = new Document(4, "Metasploit Penetration Testing Cookbook", "Abhinav Singh", "Packt", "http://www.it-docs.net/ddata/3788.pdf", 250, 3, 1);
		documents.add(document6);
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
	
	// Find documents by Name
	public List<Document> findByName(String name) {
		List<Document> temp_documents = new ArrayList<>();
		for(Document document:documents) {
			if(document.getName().toLowerCase().contains(name.toLowerCase())) {
				temp_documents.add(document);
			}
		}
		return temp_documents;
	}
	
	// Find documents by Author
	public List<Document> findByAuthor(String author) {
		List<Document> temp_documents = new ArrayList<>();
		for(Document document:documents) {
			if(document.getAuthor().toLowerCase().contains(author.toLowerCase())) {
				temp_documents.add(document);
			}
		}
		return temp_documents;
	}
	
	// Find documents by Location
	public List<Document> findByLocation(String location) {
		List<Document> temp_documents = new ArrayList<>();
		for(Document document:documents) {
			if(document.getLocation().toLowerCase().contains(location.toLowerCase())) {
				temp_documents.add(document);
			}
		}
		return temp_documents;
	}
	
	// Find documents by Publisher
	public List<Document> findByPublisher(String publisher) {
		List<Document> temp_documents = new ArrayList<>();
		for(Document document:documents) {
			if(document.getPublisher().toLowerCase().contains(publisher.toLowerCase())) {
				temp_documents.add(document);
			}
		}
		return temp_documents;
	}
	
	// Find documents by User ID
	public List<Document> findByUserId(int user_id) {
		List<Document> temp_documents = new ArrayList<>();
		for(Document document:documents) {
			if(document.getUser_id() == user_id) {
				temp_documents.add(document);
			}
		}
		return temp_documents;
	}
	
	// Find documents by Type ID
	public List<Document> findByTypeId(int type_id) {
		List<Document> temp_documents = new ArrayList<>();
		for(Document document:documents) {
			if(document.getType_id() == type_id) {
				temp_documents.add(document);
			}
		}
		return temp_documents;
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
