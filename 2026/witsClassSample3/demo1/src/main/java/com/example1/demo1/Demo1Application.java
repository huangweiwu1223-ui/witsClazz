package com.example1.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
// @MapperScan("com.example1.demo1.mapper")
public class Demo1Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
