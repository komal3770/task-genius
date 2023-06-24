package com.taskgenius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TaskGeniusApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskGeniusApplication.class, args);
	}

}
