package com.example.spring_jaxb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.spring_jaxb.jaxb.ObjectToXml;
import com.example.spring_jaxb.jaxb.XmlToObject;

@SpringBootApplication
public class SpringJaxbApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringJaxbApplication.class, args);
	}
}
