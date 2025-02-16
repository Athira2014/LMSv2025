package com.athira.demo.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync 
public class AppConfig {
	
	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(2);
	}

}
