package com.pijush.springbatch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class FirstItemWriter implements ItemWriter<Integer> {

	@Override
	public void write(Chunk<? extends Integer> chunk) throws Exception {
		
		System.out.println("Inside item writer");
		chunk.getItems().stream().forEach(System.out::println);
		
	}

}
