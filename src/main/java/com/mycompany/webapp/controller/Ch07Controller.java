package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.dto.Ch07City;
import com.mycompany.webapp.dto.Ch07Cloth;
import com.mycompany.webapp.dto.Ch07Member;
import com.mycompany.webapp.dto.Ch07Board;

@Controller
@RequestMapping("/ch07")
public class Ch07Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch07Controller.class);

	@RequestMapping("/content")
	public String content() {
		return "ch07/content";
	}

	@GetMapping("/saveData")
	public String saveData(HttpServletRequest request) {
		logger.info("실행");

		// Request 범위에 데이터를 저장한다.
		request.setAttribute("requestData", "자바");

		// Session 범위에 데이터를 저장한다.
		HttpSession session = request.getSession();
		session.setAttribute("sessionData", "자바스크립트");

		// Application 범위에 데이터를 저장한다.
		ServletContext application = request.getServletContext();
		application.setAttribute("applicationData", "백엔드");

		return "ch07/readData";
	}

	@GetMapping("/readData")
	public String readData() {
		logger.info("실행");
		return "ch07/readData";
	}

	@GetMapping("/objectSaveAndRead1")
	public String objectSaveAndRead1(HttpServletRequest request) {
		logger.info("실행");

		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");

		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);

		request.setAttribute("member", member);

		return "ch07/objectRead";
	}

	@GetMapping("/objectSaveAndRead2")
	public ModelAndView objectSaveAndRead2(HttpServletRequest request) {
		logger.info("실행");

		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");

		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);

		ModelAndView mav = new ModelAndView();
		mav.addObject("member", member); // request 범위에 저장함
		mav.setViewName("ch07/objectRead");

		return mav;
	}

	@GetMapping("/objectSaveAndRead3")
	public String objectSaveAndRead3(Model model) {
		logger.info("실행");

		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");

		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);

		model.addAttribute("member", member); // request 범위에 저장함

		return "ch07/objectRead";
	}

	@GetMapping("/useJstl1")
	public String useJstl1(Model model) {
		logger.info("실행");

		String[] languages = { "Java", "JavaScript", "SpringFramework", "Vue" };
		model.addAttribute("langs", languages);

		return "ch07/useJstl1";
	}

	@GetMapping("/useJstl2")
	public String useJstl2(Model model) {
		logger.info("실행");
		List<Ch07Board> list = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			list.add(new Ch07Board(i, "제목" + i, "내용" + i, "글쓴이" + i, new Date()));
		}

		model.addAttribute("boardList", list);

		return "ch07/useJstl2";
	}

	@ModelAttribute("colors") // colors라는 이름으로 반환값을 저장, request 범위이다.
	public String[] getColors() {
		logger.info("실행");
		String[] colors = { "Red", "Green", "Blue", "Yellow", "Pink" };
		return colors;
	}

	@GetMapping("/useModelAttribute1")
	public String useModelAttribute1() {
		logger.info("실행");

		/*
		 * 이 부분이 위의 ModelAttribute 어노테이션과 같은 효과를 낸다.
		 * String[] colors = { "Red", "Green", "Blue", "Yellow", "Pink" };
		 * model.addAttribute(colors);
		 */

		return "ch07/useModelAttribute";
	}

	@GetMapping("/useModelAttribute2")
	public String useModelAttribute2() {
		logger.info("실행");
		return "ch07/useModelAttribute";
	}

	@GetMapping("/argumentSaveAndRead1")
	public String argumentSaveAndRead1(@ModelAttribute("kind") String kind, @ModelAttribute("sex") String sex) {
		logger.info("실행");

		logger.info("kind: " + kind);
		logger.info("sex: " + sex);

		return "ch07/argumentRead1";
	}

	@GetMapping("/argumentSaveAndRead2")
	public String argumentSaveAndRead2(@ModelAttribute("cloth") Ch07Cloth cloth) {
		logger.info("실행"); // 위처럼 Attribute 이름을 주지 않아도 ch07cloth(디폴트)로 접근할 수 있다.

		logger.info("kind: " + cloth.getKind());
		logger.info("sex: " + cloth.getSex());

		return "ch07/argumentRead2";
	}
}