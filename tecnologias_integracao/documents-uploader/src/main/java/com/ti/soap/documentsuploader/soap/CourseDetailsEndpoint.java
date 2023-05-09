package com.ti.soap.documentsuploader.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ti.soap.documentsuploader.bean.Course;
import com.ti.soap.documentsuploader.courses.CourseDetails;
import com.ti.soap.documentsuploader.courses.DeleteCourseDetailsRequest;
import com.ti.soap.documentsuploader.courses.DeleteCourseDetailsResponse;
import com.ti.soap.documentsuploader.courses.GetAllCourseDetailsRequest;
import com.ti.soap.documentsuploader.courses.GetAllCourseDetailsResponse;
import com.ti.soap.documentsuploader.courses.GetCourseDetailsRequest;
import com.ti.soap.documentsuploader.courses.GetCourseDetailsResponse;
import com.ti.soap.documentsuploader.soap.service.CourseDetailsService.Status;
import com.ti.soap.documentsuploader.soap.exception.CourseNotFoundException;
import com.ti.soap.documentsuploader.soap.service.CourseDetailsService;


@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		
		Course course = service.findById(request.getId());
		
		if(course==null)
			throw new CourseNotFoundException("Invalid Course ID: " + request.getId());
		
		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		response.setCourseDetails(mapCourse(course));
		
		return response;
	}
	
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		
		for(Course course:courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		
		List<Course> courses = service.findAll();
		
		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace = "http://www.documentsuploader.soap.ti.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

		Status status = service.deleteById(request.getId());

		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));

		return response;
	}

	private com.ti.soap.documentsuploader.courses.Status mapStatus(Status status) {
		if (status == Status.FAILURE)
			return com.ti.soap.documentsuploader.courses.Status.FAILURE;
		return com.ti.soap.documentsuploader.courses.Status.SUCCESS;
	}
}