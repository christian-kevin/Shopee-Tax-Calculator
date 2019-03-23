package com.kevin.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class Application {
	private Environment env;

	@Autowired
	public Application(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
