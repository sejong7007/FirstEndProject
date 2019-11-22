package com.testtwo.web.cus;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {
	public int rowCount();
	public List<Customer> selectAll();
}

