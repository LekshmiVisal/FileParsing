package com.strabag.fileParsingApp.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import com.strabag.fileParsingApp.service.ImportService;

import java.nio.charset.StandardCharsets;

@WebMvcTest(CSVController.class)
//@ImportAutoConfiguration // Include auto-configuration
//@Import({ImportService.class, CSVController.class}) // Import necessary components

//@SpringBootTest
//@AutoConfigureMockMvc


public class CSVControllerTest {

	@Autowired
	private MockMvc mockMvc;
	

    public final ImportService importService;
	
	@Autowired
    public CSVControllerTest(ImportService importService) {
        this.importService = importService;
    }
	
	@Test
	public void testImportCSV() throws Exception {
        
		// Prepare a sample CSV file
		String csvData = "Section name,Class 1 name,Class 1 code,Class 2 name,Class 2 code\n"
				+ "Section 1,Geo Class 1,GC1,Geo Class 2,GC2\n" + "Section 2,Geo Class 2,GC2\n"
				+ "Section 3,Geo Class 5,GCX7";
		byte[] content = csvData.getBytes(StandardCharsets.UTF_8);
		MockMultipartFile file = new MockMultipartFile("file", "sample.csv", "text/csv", content);

		// Perform the file upload
		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/import").file(file))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("CSV file imported successfully!"));

		// Add additional assertions as needed to verify the behavior of the import
		// process
	}
}
