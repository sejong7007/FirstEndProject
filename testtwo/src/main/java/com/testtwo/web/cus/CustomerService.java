package com.testtwo.web.cus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testtwo.web.pxy.Proxy;

@Component
public class CustomerService extends Proxy{

	@Autowired CustomerMapper customerMapper;
	
	public String test() {
		
		//Arrays.asList(1,2,3,4,5).stream().forEach(System.out::print);
		//IntStream.range(101, 200).forEach(System.out::print);
		new Random().ints().limit(5).forEach(System.out::println);
		
		return string(10);
	}
	
	public String selectAll() {
		
		customerMapper.selectAll().forEach(System.out::print);
		
		List<Customer> ls = customerMapper.selectAll();
		
		List<String> ls2 = new ArrayList<>();
		for(int i=0 ; i<ls.size() ; i++) {
			ls2.add(ls.get(i).getMid());
		}
		Stream.of(ls2)
		.sorted()
		.forEach(System.out::println);
		
		
		return string(10);
	}
	
	
	
}
