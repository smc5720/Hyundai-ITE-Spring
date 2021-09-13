package com.mycompany.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

@Controller
@RequestMapping("/ch08")
@SessionAttributes({ "inputForm" })
public class Ch08Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);

	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}

	@GetMapping(value = "/saveData", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String saveData(String name, HttpSession session) {
		logger.info("실행");

		session.setAttribute("name", name);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		return json;
	}

	@GetMapping(value = "/readData", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String readData(HttpSession session, @SessionAttribute("name") String name) {
		logger.info("실행");

		// 방법 1
		// String name = (String)session.getAttribute("name");

		// 방법 2
		// @SessionAttribute String name

		logger.info("name : " + name);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString();
		return json;
	}

	@GetMapping("/login")
	public String loginForm() {
		logger.info("실행");

		return "/ch08/loginForm";
	}

	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		logger.info("실행");

		if (mid.equals("spring") && mpassword.equals("12345")) {// 로그인 정보가 맞으면
			session.setAttribute("sessionMid", mid); // 세션에 mid
		}
		return "redirect:/ch08/content";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("실행");

		// 방법1
		session.removeAttribute("sessionMid");// 한가지 속성만 지움

		// 방법2
		// session.invalidate();//세션객체를 삭제함

		return "redirect:/ch08/content";
	}

	@PostMapping(value = "/loginAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		logger.info("실행");
		String result = "";

		if (!mid.equals("spring")) {
			result = "wrongMid";
		} else if (!mpassword.equals("12345")) {
			result = "wrongMpassword";
		} else {
			result = "success";
			session.setAttribute("sessionMid", mid);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		String json = jsonObject.toString();
		return json;
	}

	/*@GetMapping(value = "/logoutAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(HttpSession session) {
		logger.info("실행");
	
		// session.invalidate();
		session.removeAttribute("sessionMid");
	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		return json;
	}*/

	@GetMapping("/logoutAjax")
	@ResponseBody
	public void logoutAjax(HttpSession session, HttpServletResponse response) throws IOException {
		logger.info("실행");

		session.invalidate();
		// session.removeAttribute("sessionMid");

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();

		// 디스패처 서블릿이 알아서 해준다.
		pw.println(json);
		// pw.flush();
		// pw.close();
	}

	// 위에 SessionAttributes에 값을 포함시켰으므로 session에 들어간다.
	// session에 이름이 없을 때만 한번 실행한다. 그 이후부터는 계속 재사용
	@ModelAttribute("inputForm")
	public Ch08InputForm getInputForm() {
		logger.info("실행");
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	}

	@GetMapping("/inputStep1")
	public String inputStep1(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		logger.info("data1: " + inputForm.getData1());
		logger.info("data2: " + inputForm.getData2());
		logger.info("data3: " + inputForm.getData3());
		logger.info("data4: " + inputForm.getData4());
		return "ch08/inputStep1";
	}

	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		logger.info("data1: " + inputForm.getData1());
		logger.info("data2: " + inputForm.getData2());
		logger.info("data3: " + inputForm.getData3());
		logger.info("data4: " + inputForm.getData4());
		return "ch08/inputStep2";
	}

	@PostMapping("/inputDone")
	public String inputDone(@ModelAttribute("inputForm") Ch08InputForm inputForm, SessionStatus sessionStatus) {
		logger.info("실행");
		logger.info("data1: " + inputForm.getData1());
		logger.info("data2: " + inputForm.getData2());
		logger.info("data3: " + inputForm.getData3());
		logger.info("data4: " + inputForm.getData4());

		// 처리 내용
		// session에 저장되어 있는 inputForm을 제거한다.
		sessionStatus.setComplete();
		// 저장 방식이 다르기 때문에 session.removeAttributes 사용하면 안된다.

		return "redirect:/ch08/content";
	}
}