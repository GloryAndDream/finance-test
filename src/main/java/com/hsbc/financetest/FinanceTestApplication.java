package com.hsbc.financetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hsbc.financetest")
public class FinanceTestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FinanceTestApplication.class, args);
	}

}
