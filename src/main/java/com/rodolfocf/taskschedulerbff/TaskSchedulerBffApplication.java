package com.rodolfocf.taskschedulerbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class TaskSchedulerBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedulerBffApplication.class, args);
	}

}