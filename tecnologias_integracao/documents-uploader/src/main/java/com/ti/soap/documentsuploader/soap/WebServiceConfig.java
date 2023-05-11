package com.ti.soap.documentsuploader.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

// Enable Web services
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
	//MessageDispatcherServlet
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
	}
	
	@Bean(name="documents")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema documentsSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		
		definition.setPortTypeName("DocumentPort");
		definition.setTargetNamespace("http://www.documentsuploader.soap.ti.com/documents");
		definition.setLocationUri("/ws");
		definition.setSchema(documentsSchema);

		return definition;
	}
	
	@Bean
	public XsdSchema documentsSchema()  {
		return new SimpleXsdSchema(new ClassPathResource("document-details.xsd"));
	}
}
