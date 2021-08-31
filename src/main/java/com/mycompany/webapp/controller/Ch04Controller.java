package com.mycompany.webapp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch04Member;
import com.mycompany.webapp.validator.Ch04MemberEmailValidator;
import com.mycompany.webapp.validator.Ch04MemberIdValidator;
import com.mycompany.webapp.validator.Ch04MemberJoinFormValidator;
import com.mycompany.webapp.validator.Ch04MemberPasswordValidator;
import com.mycompany.webapp.validator.Ch04MemberTelValidator;

@Controller
@RequestMapping("/ch04")
public class Ch04Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch04Controller.class);

	@RequestMapping("/content")
	public String content() {
		return "ch04/content";
	}

	@PostMapping("/method1")
	public String method1() {
		return "redirect:/ch04/content";
	}

	@InitBinder("joinForm")
	public void joinFormSetValidate(WebDataBinder binder) {
		logger.info("실행");
		// binder.setValidator(new Ch04MemberJoinFormValidator());
		binder.addValidators(new Ch04MemberIdValidator(), new Ch04MemberPasswordValidator(),
				new Ch04MemberEmailValidator(), new Ch04MemberTelValidator());
	}

	@PostMapping("/join")
	public String join(@ModelAttribute("joinForm") @Valid Ch04Member member, BindingResult bindingResult) {
		// 기본적으로 관리하는 이름은 클래스 이름에서 맨 앞 글자를 소문자로 바꾼 것
		// BindingResult(또는 Errors)는 데이터를 검사한 뒤의 결과를 의미한다.
		logger.info("실행");
		if (bindingResult.hasErrors()) {
			logger.info("다시 입력 폼 제공 + 에러 메세지");
			return "ch04/content";
		} else {
			logger.info("정상 요청 처리 후 응답 제공");
			return "redirect:/";
		}
	}

	@InitBinder("loginForm")
	public void loginFormSetValidate(WebDataBinder binder) {
		logger.info("실행");
		// binder.setValidator(new Ch04MemberJoinFormValidator());
		binder.addValidators(new Ch04MemberIdValidator(), new Ch04MemberPasswordValidator());
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") @Valid Ch04Member member, Errors errors) {
		logger.info("실행");
		if (errors.hasErrors()) {
			logger.info("다시 입력 폼 제공 + 에러 메세지");
			// forward
			return "ch04/content";
		} else {
			logger.info("정상 요청 처리 후 응답 제공");
			// redirect
			return "redirect:/";
		}
	}
}