package com.alimuya.wxlife.access;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class Test {
	
	@RequestMapping("/tt")
	@ResponseBody
	public Book test(@RequestBody Book book){
		book.setId("run!!");
		return book;
	}
	
	@RequestMapping("/test")
	public void test2(){
		System.out.println("dsaaaaaaaaaaaaa444444444");
		Logger.getLogger(getClass()).info("dsaaaaaaaaaa333333333");
		LoggerFactory.getLogger(getClass()).info("sdaaaaaaaaa");
	}
	
	
	public class Book{
		private String id;
		private String title;
		private String price;
		
		public Book(){
			
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
	}
}
