package com.testtwo.web.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testtwo.web.pxy.Box;
import com.testtwo.web.pxy.CrawlingProxy;
import com.testtwo.web.pxy.Inventory;
import com.testtwo.web.pxy.PageProxy;

@RestController
@RequestMapping("/crawler")
public class CrawlerCtrl {

	@Autowired CrawlingProxy cpxy;
	@Autowired Inventory<HashMap<String, String>> inventory;
	@Autowired PageProxy pager;
	@Autowired Box<Object> box;
	
	@GetMapping("/naver")
	public ArrayList<HashMap<String, String>> naverCrawling(){
		System.out.println("naver 들어옴");
		return cpxy.engCrawling("https://endic.naver.com/?sLn=kr");
	}
	
	@GetMapping("/cgv")
	public ArrayList<HashMap<String, String>> cgvCrawling(){
		System.out.println("cgv 들어옴");
		return cpxy.cgvCrawling("http://www.cgv.co.kr/movies/?lt=3");
	}
	
	@GetMapping("/bugs/{pageNo}")
	public Map<?,?> bugsCrawling(@PathVariable String pageNo){
		System.out.println("bugs 들어옴");
		ArrayList<HashMap<String, String>> list = cpxy.bugsCrawling("https://music.bugs.co.kr/chart/track/week/total?chartdate=20191111");
		pager.setRowCount(list.size());
		pager.setPageSize(10);
		pager.setBlockSize(5);
		pager.setNowPage(Integer.parseInt(pageNo)-1);
		pager.paging();
		ArrayList<HashMap<String, String>> temp = new ArrayList<>();
		for(int i=pager.getStartRow() ; i <= pager.getEndRow() ; i++) {
			temp.add(list.get(i));
		}
		box.put("pager",pager);
		box.put("list", temp);
				
		return box.get();
	}
}
