package com.strabag.fileParsingApp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;

@Component
@Service
public class ImportService {

	@Value("${import.folder}")
    private String importFolder;

    @PostConstruct
    public void init() {
    	System.out.println("importFolder in ImportService is: " + importFolder);
    }
    
    public void setImportFolder(String importFolder) {
        this.importFolder = importFolder;
    }
    
    public String getImportFolder() {
        return importFolder;
    }
}
