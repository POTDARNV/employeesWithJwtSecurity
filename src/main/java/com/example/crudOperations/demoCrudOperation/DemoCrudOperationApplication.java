package com.example.crudOperations.demoCrudOperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DemoCrudOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCrudOperationApplication.class, args);
		System.out.println("Done");
	}

}
