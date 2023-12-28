package com.pijush.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Before job execution......");
		System.out.println("Job name "+jobExecution.getJobInstance().getJobName());
		System.out.println("Job params "+jobExecution.getJobParameters());
		JobExecutionListener.super.beforeJob(jobExecution);
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("After job execution......");
		System.out.println("Job name "+jobExecution.getJobInstance().getJobName());
		System.out.println("Job params "+jobExecution.getJobParameters());
		JobExecutionListener.super.afterJob(jobExecution);
	}
	
	

}
