package com.testtwo.web.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.testtwo.web.cfg.ServletConfig;
import com.testtwo.web.cus.CustomerController;
import com.testtwo.web.cus.CustomerService;

import jdk.nashorn.internal.ir.annotations.Ignore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServletConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class CustomerControllerTest {

	@Autowired CustomerController customerController;
	@Autowired CustomerService customerService;
	
	@Ignore
	public void testRowCount() {
		assertThat(customerController.rowCount(), not(equalTo(0)));
	}

	
}
