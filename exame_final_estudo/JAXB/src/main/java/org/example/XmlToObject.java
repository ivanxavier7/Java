package org.example;

import java.io.File;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class XmlToObject {
    public static void main(String[] args) {
        try {
            File file = new File("employee.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Employee e=(Employee) jaxbUnmarshaller.unmarshal(file);
            System.out.println(e.getId()+" "+e.getName()+" "+e.getSalary());

        } catch (JAXBException e) {e.printStackTrace(); }

    }
}