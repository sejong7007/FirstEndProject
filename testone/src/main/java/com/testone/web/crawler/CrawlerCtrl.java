package com.testone.web.crawler;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crawler")
public class CrawlerCtrl {

	@GetMapping("/naver")
	public HashMap<String, Object> naverCrawling(){
		System.out.println("Naver crawling");
		HashMap<String, Object> map = new HashMap<>();
		map.put("msg", "SUCCESS");
		return map;
	}
	
	@GetMapping("/cgv")
	public HashMap<String, Object> cgvCrawling(){
		System.out.println("cgv crawling");
		HashMap<String, Object> map = new HashMap<>();
		map.put("msg", "SUCCESS");
		return map;
	}
	
	@GetMapping("/bugs")
	public HashMap<String, Object> bugsCrawling(){
		System.out.println("bugs crawling");
		HashMap<String, Object> map = new HashMap<>();
		map.put("msg", "SUCCESS");
		return map;
	}
	
}
