package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch16Account;
import com.mycompany.webapp.service.Ch16AccountService;

@Controller
@RequestMapping("/ch16")
public class Ch16Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch16Controller.class);

	@Resource
	private Ch16AccountService accountService;
	
	@RequestMapping("/content")
	public String content(Model model) {
		logger.info("실행");
		List<Ch16Account> accounts = accountService.getAccounts();
		model.addAttribute("list", accounts);
		
		return "ch16/content";
	}
	
	@RequestMapping("/transaction1")
	public String transaction1(int fromAno, int toAno, int amount) {
		logger.info("실행");
		accountService.transfer1(fromAno, toAno, amount);
		return "redirect:/ch16/content";
	}
	
	@RequestMapping("/transaction2")
	public String transaction2(int fromAno, int toAno, int amount) {
		logger.info("실행");
		accountService.transfer2(fromAno, toAno, amount);
		return "redirect:/ch16/content";
	}
}
