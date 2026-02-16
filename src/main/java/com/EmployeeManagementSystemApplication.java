package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		System.out.println("DEBUG: PORT Env Var = " + System.getenv("PORT"));
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

}
