package com.pijush.springbatch.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JobService {
	
	@Autowired
	private JobLauncher jobLauncher;

	@Qualifier("firstJob")
	@Autowired
	private Job firstJob;

	@Qualifier("secondJob")
	@Autowired
	private Job secondJob;

	@Async
	public void runJob(String jobName,Map<String, String> param) {
		// Way 1 to create jobParameters
		JobParameters jobParameters1 = new JobParametersBuilder()
				.addString("Param1",param.get("param1"))
				.toJobParameters();
//		JobParameters jobParameters1 = new JobParametersBuilder().addLong("Start at - ", System.currentTimeMillis())
//				.toJobParameters();

		// Way 2 to create jobParameters
		Map<String, JobParameter<?>> params = new HashMap<>();
		params.put("Started at", new JobParameter<Long>(System.currentTimeMillis(), Long.class));

		JobParameters jobParameters2 = new JobParameters(params);
		
		JobExecution jobExecution=null;

		try {

			if (jobName.equals("FirstJob")) {
				jobExecution = jobLauncher.run(firstJob, jobParameters1);
			} else if (jobName.equals("SecondJob")) {
				jobExecution = jobLauncher.run(secondJob, jobParameters2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Job Execution Id - "+jobExecution.getId());
	}

}
