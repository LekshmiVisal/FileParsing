package com.strabag.fileParsingApp.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

//import com.strabag.fileParsingApp.repository.JobStatusRepository;
import com.strabag.fileParsingApp.controller.CSVController;
import com.strabag.fileParsingApp.model.JobStatus;

@Service
public class FileParsingService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileParsingService.class);

    @Autowired
    private JobDetailsRepository jobDetailsRepository;
    
    @Autowired
    private FileDetailsRepository fileDetailsRepository;
    
    @Autowired
    private JobStatusRepository jobStatusRepository;
    
    @Async
    public long parseAndSaveFile( MultipartFile file) throws IOException, ParseException {
        // Perform file parsing asynchronously
        // ...	
    	logger.info("Entering parseAndSaveFile()");
    	long jobId =0;
    	LocalDateTime currentDateTime = LocalDateTime.now();
  	  
        JobDetails jobDetails = new JobDetails();
        //jobDetails.setJobId(jobId);
        jobDetails.setFileName(file.getName());
        jobDetails.setOperationName("register-job");
        jobDetails.setStatus("Saving");
        jobDetails.setDate(currentDateTime);
        
      //saves job details to the JobDetails
        JobDetails savedJobDetails = jobDetailsRepository.save(jobDetails);
        logger.info("JobDetails saved");
        
        jobId  = savedJobDetails.getJobId();
        logger.info("Generated jobId: " + jobId);
       
        
    	List<FileDetails> fileDetailsArray = new ArrayList<>();
    	  try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) 
    	  {
              String line;
              while ((line = reader.readLine()) != null) {
                  String[] data = line.split("\\|");

                  FileDetails fileDetails = new FileDetails();
                  fileDetails.setJobId(jobId);
                  fileDetails.setSection_name(data[0]);
                  fileDetails.setClass1_name(data[1]);
                  fileDetails.setClass1_code(data[2]);
                  fileDetails.setClass2_name(data[3]);
                  fileDetails.setClass2_code(data[4]);

                  fileDetailsArray.add(fileDetails);
                  
                  logger.info("fileDetails added " + jobId);
                  //JobStatusRepository.save(jobId, section);
              }
        
    	  List<FileDetails> savedFileDetails = fileDetailsRepository.saveAll(fileDetailsArray);
    	  
    	  logger.info("fileDetails saved to DB");
    	  return jobId;
         // String jobId = generateJobId();
          // You can perform additional actions with the saved sections or jobId
    	  }catch (Exception e) {
  			// Handle exceptions and return an appropriate error response

  			logger.error("Failed to parse and save CSV file", e);

  			return 0;
  		}
         
      }
    public Optional<JobStatus> getJobStatus(Long jobId) {
        return jobStatusRepository.findById(jobId);
    }
}
