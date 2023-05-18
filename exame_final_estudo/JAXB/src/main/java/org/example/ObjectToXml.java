package org.example;

import java.io.FileOutputStream;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;


public class ObjectToXml {
    public static void main(String[] args) throws Exception{
        JAXBContext contextObj = JAXBContext.newInstance(Employee.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Employee emp1=new Employee(1,"Vimal Jaiswal",50000);

        marshallerObj.marshal(emp1, new FileOutputStream("employee.xml"));

    }
}