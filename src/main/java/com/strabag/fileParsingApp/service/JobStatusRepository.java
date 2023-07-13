package com.strabag.fileParsingApp.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strabag.fileParsingApp.model.JobStatus;

@Repository
public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {
}