package com.strabag.fileParsingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@ComponentScan(basePackages = "com.strabag.fileParsingApp")
public class FileParsingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileParsingApplication.class, args);
	}

}
