package com.ec.facilitator.site;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.CharacterEncodingFilter;

@ImportResource("file:./conf/beans/beans.release.xml")
@ComponentScan({ "com.ec.facilitator", })
@EnableAutoConfiguration(exclude = VelocityAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		System.out.println(Application.class.getResource("/"));
		SpringApplication.run(Application.class, args);
		System.out.println("*****************start completed enjoy it************************");
	}

	// 显示注入EncodingFilter, Form提交使用UTF-8编码
	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	// 显示注入Tomcat的Connector中uri的encoding为utf-8
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				connector.setURIEncoding("UTF-8");
			}
		});
		return factory;
	}
}
