package com.pijush.springbatch.controller;

import java.util.Map;

import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pijush.springbatch.service.JobService;

@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	private JobService jobService;
	
	@Autowired
	private JobOperator jobOperator;

	@PostMapping(value="/start/{jobName}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String startJob(@PathVariable String jobName, @RequestParam Map<String,String> map) {
		
		System.out.println(map);
		
		jobService.runJob(jobName,map);
		return "Job started...";
	}
	
	@GetMapping("/stop/{jobExecutionId}")
	public String stopJob(@PathVariable long jobExecutionId) {
		try {
			jobOperator.stop(jobExecutionId);
		} catch (NoSuchJobExecutionException | JobExecutionNotRunningException e) {
			e.printStackTrace();
		}
		return "Job stopped.";
	}

}
