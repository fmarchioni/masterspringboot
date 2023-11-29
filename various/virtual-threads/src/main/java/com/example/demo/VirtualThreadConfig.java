package com.example.demo;

import java.util.concurrent.Executors;

import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 
@Configuration
public class VirtualThreadConfig {

	/**
	 * Bean that configures Tomcat to use virtual threads.
	 *
	 * @return A {@link TomcatProtocolHandlerCustomizer} that configures Tomcat to
	 *         use virtual threads.
	 */
	@Bean
	TomcatProtocolHandlerCustomizer<?> tomcatProtocolHandlerCustomizer() {
		return protocolHandler -> {
			protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
		};
	}
}