package com.pijush.springbatch.config;

import org.springframework.batch.item.ItemProcessor;

import com.pijush.springbatch.entity.Customer;

public class CustomerProccessor implements ItemProcessor<Customer, Customer> {

	@Override
	public Customer process(Customer item) throws Exception {
		if(item.getCountry().equals("China"))
			return item;
		return null;
	}
	
	

}
