package com.example.demo.config;

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

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet (ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/service/*");
    }

    @Bean(name = "studentDetailsWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema studentSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("StudentDetailsPort");
        wsdl11Definition.setLocationUri("/service/student-details");
        wsdl11Definition.setTargetNamespace("http://www.demo.example.com/xml/school");
        wsdl11Definition.setSchema(studentSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema studentSchema(){
        return new SimpleXsdSchema(new ClassPathResource("student.xsd"));
    }

    @Bean(name = "bookWsdl")
    public DefaultWsdl11Definition bookWsdl11Definition(XsdSchema bookSchema){
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("BookPort");
        defaultWsdl11Definition.setLocationUri("/service/book");
        defaultWsdl11Definition.setTargetNamespace("http://www.demo.example.com/xml/school/book");
        defaultWsdl11Definition.setSchema(bookSchema);
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema bookSchema(){
        return new SimpleXsdSchema(new ClassPathResource("book.xsd"));
    }
}
