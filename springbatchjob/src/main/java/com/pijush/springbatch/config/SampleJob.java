package com.pijush.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.pijush.springbatch.listener.FirstJobListener;
import com.pijush.springbatch.listener.FirstStepListener;
import com.pijush.springbatch.service.SecondTasklet;

@Configuration

public class SampleJob {
	
	
	@Autowired
	private SecondTasklet secondTasklet;
	
	@Autowired
	private FirstJobListener firstJobListener;
	
	@Autowired
	private FirstStepListener firstStepListener;
	
	
	@Bean
	public Job firstJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("First Job", jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(firstStep(jobRepository, transactionManager))
				.next(secondStep(jobRepository, transactionManager))
				.listener(firstJobListener)
				.build();
	}
	
	private Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("First Step", jobRepository)
				.tasklet(firstTask(), transactionManager)
				.listener(firstStepListener)
				.build();
	}
	
	private Tasklet firstTask() {
		return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("This is the first Tasklet step");
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	private Step secondStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("Second Step", jobRepository)
				.tasklet(secondTasklet, transactionManager)
				.build();
	}

	/*
	 * private Tasklet secondTask() { return new Tasklet() {
	 * 
	 * @Override public RepeatStatus execute(StepContribution contribution,
	 * ChunkContext chunkContext) throws Exception {
	 * System.out.println("This is the second Tasklet Step"); return
	 * RepeatStatus.FINISHED;
	 * 
	 * } }; }
	 */

}
