package com.ti.soap.documentsuploader.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ti.soap.documentsuploader.bean.Document;
import com.ti.soap.documentsuploader.bean.DocumentType;
import com.ti.soap.documentsuploader.bean.User;
import com.ti.soap.documentsuploader.documents.CreateDocumentDetailsRequest;
import com.ti.soap.documentsuploader.documents.CreateDocumentDetailsResponse;
import com.ti.soap.documentsuploader.documents.CreateDocumentTypeDetailsRequest;
import com.ti.soap.documentsuploader.documents.CreateDocumentTypeDetailsResponse;
import com.ti.soap.documentsuploader.documents.CreateUserDetailsRequest;
import com.ti.soap.documentsuploader.documents.CreateUserDetailsResponse;
import com.ti.soap.documentsuploader.documents.DeleteDocumentDetailsRequest;
import com.ti.soap.documentsuploader.documents.DeleteDocumentDetailsResponse;
import com.ti.soap.documentsuploader.documents.DeleteDocumentTypeDetailsRequest;
import com.ti.soap.documentsuploader.documents.DeleteDocumentTypeDetailsResponse;
import com.ti.soap.documentsuploader.documents.DeleteUserDetailsRequest;
import com.ti.soap.documentsuploader.documents.DeleteUserDetailsResponse;
import com.ti.soap.documentsuploader.documents.DocumentDetails;
import com.ti.soap.documentsuploader.documents.DocumentTypeDetails;
import com.ti.soap.documentsuploader.documents.GetAllDocumentTypesDetailsRequest;
import com.ti.soap.documentsuploader.documents.GetAllDocumentTypesDetailsResponse;
import com.ti.soap.documentsuploader.documents.GetAllDocumentsDetailsRequest;
import com.ti.soap.documentsuploader.documents.GetAllDocumentsDetailsResponse;
import com.ti.soap.documentsuploader.documents.GetAllUsersDetailsRequest;
import com.ti.soap.documentsuploader.documents.GetAllUsersDetailsResponse;
import com.ti.soap.documentsuploader.documents.GetDocumentDetailsRequest;
import com.ti.soap.documentsuploader.documents.GetDocumentDetailsResponse;
import com.ti.soap.documentsuploader.documents.GetDocumentTypeDetailsRequest;
import com.ti.soap.documentsuploader.documents.GetDocumentTypeDetailsResponse;
import com.ti.soap.documentsuploader.documents.GetUserDetailsRequest;
import com.ti.soap.documentsuploader.documents.GetUserDetailsResponse;
import com.ti.soap.documentsuploader.documents.UpdateDocumentDetailsRequest;
import com.ti.soap.documentsuploader.documents.UpdateDocumentDetailsResponse;
import com.ti.soap.documentsuploader.documents.UpdateDocumentTypeDetailsRequest;
import com.ti.soap.documentsuploader.documents.UpdateDocumentTypeDetailsResponse;
import com.ti.soap.documentsuploader.documents.UpdateUserDetailsRequest;
import com.ti.soap.documentsuploader.documents.UpdateUserDetailsResponse;
import com.ti.soap.documentsuploader.documents.UserDetails;
import com.ti.soap.documentsuploader.soap.service.DocumentDetailsService;
import com.ti.soap.documentsuploader.soap.service.DocumentDetailsService.Status;
import com.ti.soap.documentsuploader.soap.service.DocumentTypeDetailsService;
import com.ti.soap.documentsuploader.soap.service.DocumentTypeDetailsService.TypeStatus;
import com.ti.soap.documentsuploader.soap.service.UserDetailsService;
import com.ti.soap.documentsuploader.soap.service.UserDetailsService.UserStatus;
import com.ti.soap.documentsuploader.soap.exception.DocumentNotFoundException;

@Endpoint
public class DocumentDetailsEndpoint {
	
	@Autowired
	DocumentDetailsService service;
	
	@Autowired
	DocumentTypeDetailsService typeService;
	
	@Autowired
	UserDetailsService userService;
	
	
	// Documents
	
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
		documentDetails.setAuthor(document.getAuthor());
		documentDetails.setPublisher(document.getPublisher());
		documentDetails.setLocation(document.getLocation());
		documentDetails.setPages(document.getPages());
		documentDetails.setTypeId(document.getType_id());
		documentDetails.setUserId(document.getUser_id());
		
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
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "CreateDocumentDetailsRequest")
	@ResponsePayload
	public CreateDocumentDetailsResponse createDocumentDetailsRequest(@RequestPayload CreateDocumentDetailsRequest request) {

		Status status = service.create(
				request.getDocumentDetails().getId(),
				request.getDocumentDetails().getName(),
				request.getDocumentDetails().getAuthor(),
				request.getDocumentDetails().getPublisher(),
				request.getDocumentDetails().getLocation(),
				request.getDocumentDetails().getPages(),
				request.getDocumentDetails().getTypeId(),
				request.getDocumentDetails().getUserId()
				);

		CreateDocumentDetailsResponse response = new CreateDocumentDetailsResponse();
		response.setStatus(mapStatus(status));

		return response;
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "UpdateDocumentDetailsRequest")
	@ResponsePayload
	public UpdateDocumentDetailsResponse updateDocumentDetailsByIdRequest(@RequestPayload UpdateDocumentDetailsRequest request) {

		Status status = service.update(
				request.getDocumentDetails().getId(),
				request.getDocumentDetails().getName(),
				request.getDocumentDetails().getAuthor(),
				request.getDocumentDetails().getPublisher(),
				request.getDocumentDetails().getLocation(),
				request.getDocumentDetails().getPages(),
				request.getDocumentDetails().getTypeId(),
				request.getDocumentDetails().getUserId()
				);

		UpdateDocumentDetailsResponse response = new UpdateDocumentDetailsResponse();
		response.setStatus(mapStatus(status));

		return response;
	}

	private com.ti.soap.documentsuploader.documents.Status mapStatus(Status status) {
		if (status == Status.FAILURE)
			return com.ti.soap.documentsuploader.documents.Status.FAILURE;
		return com.ti.soap.documentsuploader.documents.Status.SUCCESS;
	}
	
	//DocumentTypes
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "GetDocumentTypeDetailsRequest")
	@ResponsePayload
	public GetDocumentTypeDetailsResponse processDocumentTypeDetailsRequest(@RequestPayload GetDocumentTypeDetailsRequest request) {
		
		DocumentType documentType = typeService.findById(request.getId());
		
		if(documentType==null)
			throw new DocumentNotFoundException("Invalid Document ID: " + request.getId());
		
		return mapDocumentTypeDetails(documentType);
	}
	
	private GetDocumentTypeDetailsResponse mapDocumentTypeDetails(DocumentType documentType) {
		GetDocumentTypeDetailsResponse response = new GetDocumentTypeDetailsResponse();
		
		response.setDocumentTypeDetails(mapDocumentType(documentType));
		
		return response;
	}
	
	private GetAllDocumentTypesDetailsResponse mapAllDocumentTypesDetails(List<DocumentType> documentTypes) {
		GetAllDocumentTypesDetailsResponse response = new GetAllDocumentTypesDetailsResponse();
		
		for(DocumentType documentType:documentTypes) {
			DocumentTypeDetails mapDocumentType = mapDocumentType(documentType);
			response.getDocumentTypeDetails().add(mapDocumentType);
		}
		
		return response;
	}
	
	private DocumentTypeDetails mapDocumentType(DocumentType documentType) {
		DocumentTypeDetails documentTypeDetails = new DocumentTypeDetails();
		
		documentTypeDetails.setId(documentType.getId());
		documentTypeDetails.setType(documentType.getType());
		documentTypeDetails.setFormat(documentType.getFormat());

		
		return documentTypeDetails;
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "GetAllDocumentTypesDetailsRequest")
	@ResponsePayload
	public GetAllDocumentTypesDetailsResponse processAllDocumentTypesDetailsRequest(@RequestPayload GetAllDocumentTypesDetailsRequest request) {
		
		List<DocumentType> documentTypes = typeService.findAll();
		
		return mapAllDocumentTypesDetails(documentTypes);
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "DeleteDocumentTypeDetailsRequest")
	@ResponsePayload
	public DeleteDocumentTypeDetailsResponse deleteDocumentTypeDetailsRequest(@RequestPayload DeleteDocumentTypeDetailsRequest request) {

		TypeStatus status = typeService.deleteById(request.getId());

		DeleteDocumentTypeDetailsResponse response = new DeleteDocumentTypeDetailsResponse();
		response.setStatus(mapTypeStatus(status));

		return response;
	}

	private com.ti.soap.documentsuploader.documents.Status mapTypeStatus(TypeStatus status) {
		if (status == TypeStatus.FAILURE)
			return com.ti.soap.documentsuploader.documents.Status.FAILURE;
		return com.ti.soap.documentsuploader.documents.Status.SUCCESS;
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "CreateDocumentTypeDetailsRequest")
	@ResponsePayload
	public CreateDocumentTypeDetailsResponse createDocumentTypeDetailsRequest(@RequestPayload CreateDocumentTypeDetailsRequest request) {

		TypeStatus status = typeService.create(
				request.getDocumentTypeDetails().getId(),
				request.getDocumentTypeDetails().getType(),
				request.getDocumentTypeDetails().getFormat()
				);

		CreateDocumentTypeDetailsResponse response = new CreateDocumentTypeDetailsResponse();
		response.setStatus(mapTypeStatus(status));

		return response;
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "UpdateDocumentTypeDetailsRequest")
	@ResponsePayload
	public UpdateDocumentTypeDetailsResponse updateDocumentTypeDetailsByIdRequest(@RequestPayload UpdateDocumentTypeDetailsRequest request) {

		TypeStatus status = typeService.update(
				request.getDocumentTypeDetails().getId(),
				request.getDocumentTypeDetails().getType(),
				request.getDocumentTypeDetails().getFormat()
				);

		UpdateDocumentTypeDetailsResponse response = new UpdateDocumentTypeDetailsResponse();
		response.setStatus(mapTypeStatus(status));

		return response;
	}
	
	
	// Users
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "GetUsersDetailsRequest")
	@ResponsePayload
	public GetUserDetailsResponse processUsersDetailsRequest(@RequestPayload GetUserDetailsRequest request) {
		
		User user = userService.findById(request.getId());
		
		if(user==null)
			throw new DocumentNotFoundException("Invalid Document ID: " + request.getId());
		
		return mapUserDetails(user);
	}
	
	private GetUserDetailsResponse mapUserDetails(User user) {
		GetUserDetailsResponse response = new GetUserDetailsResponse();
		
		response.setUserDetails(mapUser(user));
		
		return response;
	}
	
	private GetAllUsersDetailsResponse mapAllUsersDetails(List<User> users) {
		GetAllUsersDetailsResponse response = new GetAllUsersDetailsResponse();
		
		for(User user:users) {
			UserDetails mapUser = mapUser(user);
			response.getUserDetails().add(mapUser);
		}
		
		return response;
	}
	
	private UserDetails mapUser(User user) {
		UserDetails userNew = new UserDetails();
		
		userNew.setId(user.getId());
		userNew.setName(user.getName());
		userNew.setType(user.getType());
		userNew.setEmail(user.getEmail());
		
		return userNew;
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "GetAllUsersDetailsRequest")
	@ResponsePayload
	public GetAllUsersDetailsResponse processAllUserDetailsRequest(@RequestPayload GetAllUsersDetailsRequest request) {
		
		List<User> users = userService.findAll();
		
		return mapAllUsersDetails(users);
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "DeleteUserDetailsRequest")
	@ResponsePayload
	public DeleteUserDetailsResponse deleteUserDetailsRequest(@RequestPayload DeleteUserDetailsRequest request) {

		UserStatus status = userService.deleteById(request.getId());

		DeleteUserDetailsResponse response = new DeleteUserDetailsResponse();
		response.setStatus(mapUserStatus(status));

		return response;
	}

	private com.ti.soap.documentsuploader.documents.Status mapUserStatus(UserStatus status) {
		if (status == UserStatus.FAILURE)
			return com.ti.soap.documentsuploader.documents.Status.FAILURE;
		return com.ti.soap.documentsuploader.documents.Status.SUCCESS;
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "CreateUserDetailsRequest")
	@ResponsePayload
	public CreateUserDetailsResponse createUserDetailsRequest(@RequestPayload CreateUserDetailsRequest request) {

		UserStatus status = userService.create(
				request.getUserDetails().getId(),
				request.getUserDetails().getName(),
				request.getUserDetails().getType(),
				request.getUserDetails().getEmail()
				);

		CreateUserDetailsResponse response = new CreateUserDetailsResponse();
		response.setStatus(mapUserStatus(status));

		return response;
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/documents", localPart = "UpdateUserDetailsRequest")
	@ResponsePayload
	public UpdateUserDetailsResponse updateUserDetailsByIdRequest(@RequestPayload UpdateUserDetailsRequest request) {

		UserStatus status = userService.update(
				request.getUserDetails().getId(),
				request.getUserDetails().getName(),
				request.getUserDetails().getType(),
				request.getUserDetails().getEmail()
				);

		UpdateUserDetailsResponse response = new UpdateUserDetailsResponse();
		response.setStatus(mapUserStatus(status));

		return response;
	}
}
