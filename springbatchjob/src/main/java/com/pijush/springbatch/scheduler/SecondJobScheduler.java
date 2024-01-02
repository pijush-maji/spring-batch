package com.pijush.springbatch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SecondJobScheduler {

	@Autowired
	private JobLauncher jobLauncher;

	@Qualifier("secondJob")
	@Autowired
	private Job secondJob;

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void secondJobStarter() {

		JobExecution jobExecution = null;
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("Started at", System.currentTimeMillis())
				.toJobParameters();
		
		try {
			jobExecution = jobLauncher.run(secondJob, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		System.out.println("Job Execution Id - "+jobExecution.getId());
	}

}
