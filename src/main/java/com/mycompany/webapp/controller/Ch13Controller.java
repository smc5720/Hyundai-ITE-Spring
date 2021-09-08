package com.mycompany.webapp.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.service.Ch13Service;
import com.mycompany.webapp.service.Ch13Service1;
import com.mycompany.webapp.service.Ch13Service2;
import com.mycompany.webapp.service.Ch13Service3;
import com.mycompany.webapp.service.Ch13Service5;

@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Controller.class);

	private Ch13Service1 ch13Service1;

	// @Autowired
	@Resource
	private Ch13Service2 ch13Service2;

	// 인터페이스를 사용하는 방법이다.
	// 인터페이스를 상속받는 애들이 여러 개면 누굴 주입할지 명시해줘야 한다.
	@Resource(name="service3")
	private Ch13Service ch13Service;

	public Ch13Controller() {
		logger.info("실행");
	}

	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch13/content";
	}

	// @Controller는 파라미터가 없는 기본 생성자만을 사용하여 생성된다.
	// 따라서 생성자 주입은 불가능하고, Setter 주입만 사용할 수 있다.
	public void setCh13Service1(Ch13Service1 ch13Service1) {
		logger.info("실행");
		this.ch13Service1 = ch13Service1;
	}

//	@Autowired
//	@Resource	
//	public void setCh13Service2(Ch13Service2 ch13Service2) {
//		logger.info("실행");
//		this.ch13Service2 = ch13Service2;
//	}

	@GetMapping("/request1")
	public String request1() {
		logger.info("실행");
		ch13Service1.method1();
		return "redirect:/ch13/content";
	}

	@GetMapping("/request2")
	public String request2() {
		logger.info("실행");
		ch13Service2.method1();
		return "redirect:/ch13/content";
	}

	@GetMapping("/request3")
	public String request3() {
		logger.info("실행");
		ch13Service.method2();
		return "redirect:/ch13/content";
	}
}