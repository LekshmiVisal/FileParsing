package com.strabag.fileParsingApp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Job_status")
public class JobStatus {

    private long jobId;
    private String status;

    public JobStatus(long jobId, String status) {
        this.jobId = jobId;
        this.status = status;
    }

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    // Getters and setters

    // You can customize this class as per your requirements
}

