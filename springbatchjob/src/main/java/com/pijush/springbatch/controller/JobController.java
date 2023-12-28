package com.pijush.springbatch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	private JobLauncher jobLauncher;

	@Qualifier("firstJob")
	@Autowired
	private Job firstJob;

	@Qualifier("secondJob")
	@Autowired
	private Job secondJob;

	@GetMapping("/start/{jobName}")
	public String startJob(@PathVariable String jobName) {
		
		//Way 1 to create jobParameters
		JobParameters jobParameters1 = new JobParametersBuilder().addLong("Start at - ", System.currentTimeMillis())
				.toJobParameters();
		
		//Way 2 to create jobParameters
		Map<String, JobParameter<?>> params = new HashMap<>();
		params.put("Started at", new JobParameter<Long>(System.currentTimeMillis(), Long.class));
		
		JobParameters jobParameters2 = new JobParameters(params);
		
		try {

			if (jobName.equals("FirstJob")) {
				jobLauncher.run(firstJob, jobParameters1);
			} else if (jobName.equals("SecondJob")) {
				jobLauncher.run(secondJob, jobParameters2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Job started...";
	}

}
