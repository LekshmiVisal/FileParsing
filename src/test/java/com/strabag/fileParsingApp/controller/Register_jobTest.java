package com.strabag.fileParsingApp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.strabag.fileParsingApp.service.FileParsingService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Register_jobTest {

    @Mock
    private FileParsingService fileParsingService;

    @InjectMocks
    private CSVController csvController;

    @Captor
    private ArgumentCaptor<MultipartFile> fileCaptor;

    private MockMvc mockMvc;
    
	@Value("${import.folder}")
    private String importFolder;

    @Test
    public void testRegisterJob() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(csvController).build();

        MockMultipartFile file = new MockMultipartFile(
                "file",
                importFolder+"/sample.csv",
                MediaType.TEXT_PLAIN_VALUE,
                "Test file content".getBytes()
        ); 

        String jobId = UUID.randomUUID().toString();

        when(fileParsingService.parseAndSaveFile(fileCaptor.capture(), org.mockito.ArgumentMatchers.anyString()))
                .thenReturn(CompletableFuture.completedFuture(null));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/register-job")
                .file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobId").value(jobId));

        verify(fileParsingService).parseAndSaveFile(fileCaptor.capture(), org.mockito.ArgumentMatchers.anyString());
        MultipartFile uploadedFile = fileCaptor.getValue();
        // Additional assertions on the uploaded file if needed
    }
}
