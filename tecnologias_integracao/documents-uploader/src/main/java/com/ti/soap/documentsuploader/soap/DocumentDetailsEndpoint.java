package com.ti.soap.documentsuploader.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ti.soap.documentsuploader.bean.Document;
import com.ti.soap.documentsuploader.documents.DeleteDocumentDetailsRequest;
import com.ti.soap.documentsuploader.documents.DeleteDocumentDetailsResponse;
import com.ti.soap.documentsuploader.documents.DocumentDetails;
import com.ti.soap.documentsuploader.documents.GetAllDocumentsDetailsRequest;
import com.ti.soap.documentsuploader.documents.GetAllDocumentsDetailsResponse;
import com.ti.soap.documentsuploader.documents.GetDocumentDetailsRequest;
import com.ti.soap.documentsuploader.documents.GetDocumentDetailsResponse;
import com.ti.soap.documentsuploader.soap.service.DocumentDetailsService;
import com.ti.soap.documentsuploader.soap.service.DocumentDetailsService.Status;
import com.ti.soap.documentsuploader.soap.exception.DocumentNotFoundException;


@Endpoint
public class DocumentDetailsEndpoint {
	
	@Autowired
	DocumentDetailsService service;
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "GetDocumentDetailsRequest")
	@ResponsePayload
	public GetDocumentDetailsResponse processDocumentDetailsRequest(@RequestPayload GetDocumentDetailsRequest request) {
		
		Document document = service.findById(request.getId());
		
		if(document==null)
			throw new DocumentNotFoundException("Invalid Document ID: " + request.getId());
		
		return mapDocumentDetails(document);
	}

	private GetDocumentDetailsResponse mapDocumentDetails(Document document) {
		GetDocumentDetailsResponse response = new GetDocumentDetailsResponse();
		
		response.setDocumentDetails(mapDocument(document));
		
		return response;
	}
	
	private GetAllDocumentsDetailsResponse mapAllDocumentsDetails(List<Document> documents) {
		GetAllDocumentsDetailsResponse response = new GetAllDocumentsDetailsResponse();
		
		for(Document document:documents) {
			DocumentDetails mapDocument = mapDocument(document);
			response.getDocumentDetails().add(mapDocument);
		}
		
		return response;
	}

	private DocumentDetails mapDocument(Document document) {
		DocumentDetails documentDetails = new DocumentDetails();
		
		documentDetails.setId(document.getId());
		documentDetails.setName(document.getName());
		documentDetails.setDescription(document.getDescription());
		return documentDetails;
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "GetAllDocumentsDetailsRequest")
	@ResponsePayload
	public GetAllDocumentsDetailsResponse processAllDocumentsDetailsRequest(@RequestPayload GetAllDocumentsDetailsRequest request) {
		
		List<Document> documents = service.findAll();
		
		return mapAllDocumentsDetails(documents);
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "DeleteDocumentDetailsRequest")
	@ResponsePayload
	public DeleteDocumentDetailsResponse deleteDocumentDetailsRequest(@RequestPayload DeleteDocumentDetailsRequest request) {

		Status status = service.deleteById(request.getId());

		DeleteDocumentDetailsResponse response = new DeleteDocumentDetailsResponse();
		response.setStatus(mapStatus(status));

		return response;
	}

	private com.ti.soap.documentsuploader.documents.Status mapStatus(Status status) {
		if (status == Status.FAILURE)
			return com.ti.soap.documentsuploader.documents.Status.FAILURE;
		return com.ti.soap.documentsuploader.documents.Status.SUCCESS;
	}
}
