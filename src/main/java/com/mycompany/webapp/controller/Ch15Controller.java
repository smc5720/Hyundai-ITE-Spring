package com.mycompany.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14MemberService.LoginResult;

@Controller
@RequestMapping("/ch15")
public class Ch15Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch15Controller.class);

	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch15/content";
	}

	@RequestMapping("/before")
	public String before() {
		logger.info("실행");
		return "redirect:/ch15/content";
	}

	@RequestMapping("/after")
	public String after() {
		logger.info("실행");
		return "redirect:/ch15/content";
	}

	@RequestMapping("/afterReturning")
	public String afterReturning() {
		logger.info("실행");
		return "redirect:/ch15/content";
	}

	@RequestMapping("/afterThrowing")
	public String afterThrowing() {
		logger.info("실행");
		if (true) {
			throw new RuntimeException("테스트 예외입니다.");
		}
		return "redirect:/ch15/content";
	}

	@RequestMapping("/around")
	public String around() {
		logger.info("실행");
		return "redirect:/ch15/content";
	}

	@Resource
	Ch14BoardService boardService;

	@RequestMapping("/runtimeCheck")
	public String runtimeCheck() {
		logger.info("실행");
		Pager pager = new Pager(10, 5, boardService.getTotalBoardNum(), 1);
		List<Ch14Board> boards = boardService.getBoards(pager);
		return "redirect:/ch15/content";
	}

	@RequestMapping("/authCheck")
	public String authCheck() {
		logger.info("실행");
		return "redirect:/ch15/content";
	}

	@GetMapping("/login")
	public String loginForm() {
		logger.info("실행");
		return "ch15/loginForm";
	}

	@Resource
	private Ch14MemberService memberService;

	@PostMapping("/login")
	public String login(Ch14Member member, Model model, HttpSession session) {
		logger.info("실행");
		LoginResult result = memberService.login(member);

		if (result == LoginResult.SUCCESS) {
			logger.info("로그인 성공");
			session.setAttribute("sessionMid", member.getMid());
			return "redirect:/";
		} else if (result == LoginResult.WRONG_ID) {
			logger.info("아이디 실패");
			String error = "가입된 아이디가 아닙니다.";
			model.addAttribute("error", error);
			return "ch15/loginForm";
		} else if (result == LoginResult.WRONG_PASSWORD) {
			logger.info("비밀번호 실패");
			String error = "잘못된 패스워드입니다.";
			model.addAttribute("error", error);
			return "ch15/loginForm";
		} else {
			logger.info("서버 오류");
			String error = "알 수 없는 이유로 로그인에 실패했습니다.";
			model.addAttribute("error", error);
			return "ch15/loginForm";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("실행");
		session.removeAttribute("sessionMid");
		return "redirect:/ch15/content";
	}

	@GetMapping("/boardList1")
	public String boardList1(Model model) {
		logger.info("실행");
		Pager pager = new Pager(5, 5, boardService.getTotalBoardNum(), 1);
		List<Ch14Board> boards = boardService.getBoards(pager);
		model.addAttribute("boards", boards);
		return "ch15/boardList";
	}

	@GetMapping(value = "/boardList2", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String boardList2(Model model) {
		logger.info("실행");
		Pager pager = new Pager(5, 5, boardService.getTotalBoardNum(), 1);
		List<Ch14Board> boards = boardService.getBoards(pager);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		
		JSONArray jsonArray = new JSONArray();
		
		for(Ch14Board board: boards) {
			JSONObject boardObject = new JSONObject();
			boardObject.put("bno", board.getBno());
			boardObject.put("btitle", board.getBtitle());
			boardObject.put("bdate", sdf.format(board.getBdate()));
			boardObject.put("mid", board.getMid());
			jsonArray.put(boardObject);
		}	
		
		jsonObject.put("boards", jsonArray);
		String json = jsonObject.toString();
		
		return json;
	}
}
