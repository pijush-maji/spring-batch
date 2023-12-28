package com.pijush.springbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {

		System.out.println("Before step ----------------");
		System.out.println("Step name - "+stepExecution.getStepName());
		System.out.println("Step context - "+stepExecution.getExecutionContext());		
		
		StepExecutionListener.super.beforeStep(stepExecution);
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {

		System.out.println("After step ----------------");
		System.out.println("Step name - "+stepExecution.getStepName());
		System.out.println("Step context - "+stepExecution.getExecutionContext());
		
		return StepExecutionListener.super.afterStep(stepExecution);
	}
	
	

}
