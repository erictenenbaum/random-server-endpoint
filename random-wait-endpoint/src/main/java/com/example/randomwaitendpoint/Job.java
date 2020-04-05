package com.example.randomwaitendpoint;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Job {	
	private String jobId;
	
	
	@JsonGetter("jobId")
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}	
}
