package com.strabag.fileParsingApp.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.strabag.fileParsingApp.service.ImportService;

import jakarta.annotation.PostConstruct;


@Controller
@RequestMapping("/api")
public class CSVController {

	private static final Logger logger = LoggerFactory.getLogger(CSVController.class);
	
	@Autowired
	private ImportService importService;
	
	// Reads the import folder value from application.properties
	@Value("${import.folder}")
    private String importFolder;
	
    @PostConstruct
    public void init() {
    	logger.info("At line 61 of init importFolder " + importFolder);
    }

	@PostMapping("/import")
	public String importCSV(@RequestParam("file") MultipartFile file) {
		
		logger.info("At line 76 importFolder " + importFolder);
		
		try {

			if (!file.isEmpty()) {
				
			String originalFilename = file.getOriginalFilename();
			
			logger.info("Received file upload request: {}", originalFilename);			
			
			String filePath = importFolder + File.separator + originalFilename;

			logger.info("filePath is " + filePath);
						
			// Parse the CSV file using a CSV library (e.g., OpenCSV or Apache Commons CSV)

			// Validate the file, such as checking size, extension, or content
			
			// Process the uploaded file
			// e.g., save it to a specific directory or perform some operations on it
		
				// Option 1: Using FileOutputStream
	            //FileOutputStream fileOutputStream = new FileOutputStream(filePath);
	           // fileOutputStream.write(file.getBytes());
	            //fileOutputStream.close();
	           
	            // Option 2: Using java.nio.file.Files
	            Path destination = Path.of(importFolder, originalFilename);
	            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
	            
				logger.info("CSV file imported successfully");

				return "CSV file imported successfully!";
			} else {

				logger.error("Failed to import CSV file");

				return "Failed to import file.";
			}

		} catch (Exception e) {
			// Handle exceptions and return an appropriate error response

			logger.error("Failed to import CSV file", e);

			return "Failed to import CSV file: " + e.getMessage();
		}
	}

}
