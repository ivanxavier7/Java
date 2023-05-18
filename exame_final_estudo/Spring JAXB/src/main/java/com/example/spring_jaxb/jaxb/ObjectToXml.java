package com.example.spring_jaxb.jaxb;

import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Component
public class ObjectToXml {
	
	@PostConstruct
    public static void run() throws Exception{
        JAXBContext contextObj = JAXBContext.newInstance(Employee.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Employee emp1=new Employee(1,"Vimal Jaiswal",50000);

        marshallerObj.marshal(emp1, new FileOutputStream("employee.xml"));
        System.out.println("File created!");
    }
}