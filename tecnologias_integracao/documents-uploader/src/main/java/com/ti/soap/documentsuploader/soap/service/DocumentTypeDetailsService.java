package com.ti.soap.documentsuploader.soap.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ti.soap.documentsuploader.bean.DocumentType;

@Component
public class DocumentTypeDetailsService {
	
	public enum TypeStatus {
		SUCCESS, FAILURE;
	}
	
	private static List<DocumentType> documentTypes = new ArrayList<>();
	
	static {
		DocumentType documentType1 = new DocumentType(1, "Spring", "10 Steps");
		documentTypes.add(documentType1);
		DocumentType documentType2 = new DocumentType(2, "Spring MVC", "10 Examples");
		documentTypes.add(documentType2);
		DocumentType documentType3 = new DocumentType(3, "Spring Boot", "6k Students");
		documentTypes.add(documentType3);
		DocumentType documentType4 = new DocumentType(4, "Maven", "Most popular maven document in Udemy");
		documentTypes.add(documentType4);
	}
	
	// Find document type by ID
	public DocumentType findById(int id) {
		for(DocumentType documentType:documentTypes) {
			if(documentType.getId() == id) {
				return documentType;
			}
		}
		return null;
	}
	
	// Find all document types
	public List<DocumentType> findAll() {
		return documentTypes;
	}
	
	// Delete document by ID
	public TypeStatus deleteById(int id) {
		Iterator<DocumentType> iterator = documentTypes.iterator();
		while (iterator.hasNext()) {
			DocumentType document = iterator.next();
			if(document.getId() == id) {
				iterator.remove();
				return TypeStatus.SUCCESS;
			}
		}
		return TypeStatus.FAILURE;
	}
	
	// Create document type
	public TypeStatus create(int id, String type, String format) {
		DocumentType document = new DocumentType(id, type, format);
		boolean documentTypeExists = false;
		
		Iterator<DocumentType> iterator = documentTypes.iterator();
		while (iterator.hasNext()) {
			DocumentType documentInDB = iterator.next();
			if(documentInDB.getId() == id) {
				documentTypeExists = true;
			}
		}
		
		if (!documentTypeExists) {
			documentTypes.add(document);
			return TypeStatus.SUCCESS;
		}
		else {
			return TypeStatus.FAILURE;
		}
	}
	
	// Update document type by ID
	public TypeStatus update(int id, String type, String format) {
		Iterator<DocumentType> iterator = documentTypes.iterator();
		DocumentType newDocument = new DocumentType(id, type, format);
		
		while (iterator.hasNext()) {
			DocumentType oldDocument = iterator.next();
			if(oldDocument.getId() == id) {
				iterator.remove();
				documentTypes.add(newDocument);
				return TypeStatus.SUCCESS;
			}
		}
		return TypeStatus.FAILURE;
	}
}
