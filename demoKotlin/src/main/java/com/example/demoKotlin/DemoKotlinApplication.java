package com.example.demoKotlin;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
@SpringBootApplication
public class DemoKotlinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoKotlinApplication.class, args);
	}
	
	@GetMapping("/hi")
	public String sayHello() {
		return "Hello, World!";
	}
	
	public void getCumulativeRevenueAndExpenses(Date dateFrom, Date dateTo,String department) {
		
	}
	

}
