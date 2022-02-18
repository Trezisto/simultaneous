package com.example.simultaneous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages = {"com.example.*"})
@SpringBootApplication
@EnableAsync
public class SimultaneousApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimultaneousApplication.class, args);
	}

}
