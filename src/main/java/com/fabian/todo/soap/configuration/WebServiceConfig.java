package com.fabian.todo.soap.configuration;

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

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> servletServletRegistrationBean(
            ApplicationContext applicationContext){
        final MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet,
                "/ws/*");
    }

    @Bean(name = "task")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schemaTask){
        final DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("TaskTodo");
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setTargetNamespace("http://www.fabian.com/todo/soap/task");
        defaultWsdl11Definition.setSchema(schemaTask);
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema schemaTask(){
        return new SimpleXsdSchema(new ClassPathResource("task.xsd"));
    }



}
