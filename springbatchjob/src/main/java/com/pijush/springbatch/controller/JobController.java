package com.pijush.springbatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pijush.springbatch.service.JobService;

@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	private JobService jobService;

	@GetMapping("/start/{jobName}")
	public String startJob(@PathVariable String jobName) {
		
		jobService.runJob(jobName);
		return "Job started...";
	}

}
