package com.example.spring_jaxb.jaxb;

import java.io.File;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

@Component
public class XmlToObject {
	
	@PostConstruct
    public static void run() {
        try {
            File file = new File("employee.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Employee e=(Employee) jaxbUnmarshaller.unmarshal(file);
            System.out.println(e.getId()+" "+e.getName()+" "+e.getSalary());

        } catch (JAXBException e) {e.printStackTrace(); }

    }
}