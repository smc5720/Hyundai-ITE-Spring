package com.mycompany.webapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 이 클래스를 정의하면 웹을 구동함과 동시에 이 객체가 만들어진다.
@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch06Controller.class);

	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}

	@RequestMapping("/forward")
	public String forward() {
		logger.info("실행");
		return "ch06/forward";
	}

	@RequestMapping("/redirect")
	public String redirect() {
		logger.info("실행");
		return "redirect:/";
	}

	@GetMapping("/getFragmentHtml")
	public String getFragmentHtml() {
		logger.info("실행");
		return "ch06/fragmentHtml";
	}

	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws Exception {
		logger.info("실행");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo5.jpg");
		String json = jsonObject.toString();
		// 응답 HTTP의 Body 부분에 json을 포함하는 코드
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
	}

	// produces 부분이 응답 헤더에 실려서 넘어간다.
	@GetMapping(value = "/getJson2", produces = "application/json; charset=UTF-8")
	@ResponseBody // response가 응답 body에 실려서 넘어간다.
	public String getJson2() {
		logger.info("실행");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		// 넘겨주는 데이터가 어떤 종류인지 produces에 명시해야 한다.
		return json;
	}

	// 동작은 하지만, 이런 식으로 하는 설계는 옳지 않다.
	@GetMapping("/getJson3")
	public String getJson3() {
		logger.info("실행");
		return "redirect:/";
	}
}