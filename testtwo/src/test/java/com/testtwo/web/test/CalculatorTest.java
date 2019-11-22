package com.testtwo.web.test;

import static org.junit.Assert.*;
import javax.servlet.ServletContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.mock.web.MockServletContext;
import  org.springframework.test.context.ContextConfiguration;
import  org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import  org.springframework.test.context.web.WebAppConfiguration;
import  org.springframework.test.web.servlet.MockMvc;
import  org.springframework.test.web.servlet.setup.MockMvcBuilders;
import  org.springframework.web.context.WebApplicationContext;

import com.testtwo.web.cfg.ServletConfig;
import com.testtwo.web.pxy.Calculator;

import  org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServletConfig.class},loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class CalculatorTest {

	@Autowired Calculator cal;
	
	@Test
	public void testSum() {
		assertThat(cal.sum(1, 4), is(equalTo(5)));
	}

	@Test
	public void testSub() {
		assertThat(cal.sub(5, 1), is(equalTo(4)));
	}

	@Test
	public void testAbc() {
		assertThat(cal.abc(-4), is(equalTo(4)));
	}

}
