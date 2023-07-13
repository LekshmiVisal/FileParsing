package com.strabag.fileParsingApp.service;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "File_details")
public class FileDetails {

	    private Long jobId;
	    private String section_name;
	    private String class1_name;
	    private String class1_code;
	    private String class2_name;
	    private String class2_code;
	    
		public Long getJobId() {
			return jobId;
		}
		public void setJobId(Long jobId) {
			this.jobId = jobId;
		}
		public String getSection_name() {
			return section_name;
		}
		public void setSection_name(String section_name) {
			this.section_name = section_name;
		}
		public String getClass1_name() {
			return class1_name;
		}
		public void setClass1_name(String class1_name) {
			this.class1_name = class1_name;
		}
		public String getClass1_code() {
			return class1_code;
		}
		public void setClass1_code(String class1_code) {
			this.class1_code = class1_code;
		}
		public String getClass2_name() {
			return class2_name;
		}
		public void setClass2_name(String class2_name) {
			this.class2_name = class2_name;
		}
		public String getClass2_code() {
			return class2_code;
		}
		public void setClass2_code(String class2_code) {
			this.class2_code = class2_code;
		}

	    
}
