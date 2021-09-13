package com.mycompany.webapp.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14MemberService.JoinResult;
import com.mycompany.webapp.service.Ch14MemberService.LoginResult;

@Controller
@RequestMapping("/ch14")
public class Ch14Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch14Controller.class);

	@Resource
	private DataSource dataSource;

	@RequestMapping("/content")
	public String content() {
		return "ch14/content";
	}

	@GetMapping("/testConnectToDB")
	public String testConnectToDB() throws Exception {
		Connection conn = dataSource.getConnection();
		// 실패하면 에러 페이지로 넘어간다.
		logger.info("연결 성공");

		// 연결 객체를 반납하기
		conn.close();
		return "redirect:/ch14/content";
	}

	@GetMapping("/testInsert")
	public String testInsert() throws Exception {
		// 커넥션 풀에서 연결 객체 하나 가져오기
		Connection conn = dataSource.getConnection();

		try {
			// 작업 처리
			String sql = "INSERT INTO board VALUES(SEQ_BNO.NEXTVAL, ?, ?, SYSDATE, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 1번째 물음표에 값을 대입한다.
			pstmt.setString(1, "오늘은 월요일");
			// 2번째 물음표에 값을 대입한다.
			pstmt.setString(2, "스트레스가 많다.");
			// 3번째 물음표에 값을 대입한다.
			pstmt.setString(3, "user");
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 연결 객체를 반납하기
		conn.close();
		return "redirect:/ch14/content";
	}

	@GetMapping("/testSelect")
	public String testSelect() throws Exception {
		// 커넥션 풀에서 연결 객체 하나 가져오기
		Connection conn = dataSource.getConnection();

		try {
			// 작업 처리
			String sql = "SELECT bno, btitle, bcontent, bdate, mid FROM board";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// SELECT 했을 때 얻어지는 표 집합을 의미한다.
			// 커서는 맨 처음 열 제목 부분을 가리킨다.
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int bno = rs.getInt("bno");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Date bdate = rs.getDate("bdate");
				String mid = rs.getString("mid");
				logger.info(bno + "\t" + btitle + "\t" + bcontent + "\t" + bdate + "\t" + mid);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 연결 객체를 반납하기
		conn.close();
		return "redirect:/ch14/content";
	}

	@GetMapping("/testUpdate")
	public String testUpdate() throws Exception {
		// 커넥션 풀에서 연결 객체 하나 가져오기
		Connection conn = dataSource.getConnection();

		try {
			// 작업 처리
			String sql = "UPDATE board SET btitle=?, bcontent=? WHERE bno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "배고파요");
			pstmt.setString(2, "점심 먹으러 언제 가요?");
			pstmt.setInt(3, 1);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 연결 객체를 반납하기
		conn.close();
		return "redirect:/ch14/content";
	}

	@GetMapping("/testDelete")
	public String testDelete() throws Exception {
		// 커넥션 풀에서 연결 객체 하나 가져오기
		Connection conn = dataSource.getConnection();

		try {
			// 작업 처리
			String sql = "DELETE FROM board WHERE bno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 연결 객체를 반납하기
		conn.close();
		return "redirect:/ch14/content";
	}

	@Resource
	private Ch14MemberService memberService;

	@GetMapping("/join")
	public String joinForm() {
		return "ch14/joinForm";
	}

	@PostMapping("/join")
	public String join(Ch14Member member, Model model) {
		logger.info("실행");
		member.setMenabled(1);
		member.setMrole("ROLE_USER");
		JoinResult jr = memberService.join(member);

		if (jr == JoinResult.SUCCESS) {
			return "redirect:/ch14/content";
		} else if (jr == JoinResult.DUPLICATED) {
			model.addAttribute("error", "중복된 아이디가 있습니다.");
			return "ch14/joinForm";
		} else {
			model.addAttribute("error", "회원가입이 실패하였습니다. 다시 시도해주세요.");
			return "ch14/joinForm";
		}
	}

	@GetMapping("/login")
	public String loginForm() {
		return "ch14/loginForm";
	}

	@PostMapping("/login")
	public String login(Ch14Member member, Model model) {
		LoginResult lr = memberService.login(member);

		if (lr == LoginResult.SUCCESS) {
			return "redirect:/ch14/content";
		} else if (lr == LoginResult.WRONG_ID) {
			model.addAttribute("error", "가입된 아이디가 없습니다.");
			return "ch14/loginForm";
		} else if (lr == LoginResult.WRONG_PASSWORD) {
			model.addAttribute("error", "비밀번호가 잘못되었습니다.");
			return "ch14/loginForm";
		} else {
			model.addAttribute("error", "알 수 없는 이유로 로그인할 수 없습니다.");
			return "ch14/loginForm";
		}
	}
}
