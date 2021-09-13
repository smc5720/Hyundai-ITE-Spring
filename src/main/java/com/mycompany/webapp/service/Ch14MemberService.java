package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14MemberDao;
import com.mycompany.webapp.dto.Ch14Member;

@Service
public class Ch14MemberService {
	private static final Logger logger = LoggerFactory.getLogger(Ch14MemberService.class);

	public enum JoinResult {
		SUCCESS, FAIL, DUPLICATED
	}

	public enum LoginResult {
		SUCCESS, FAIL, WRONG_ID, WRONG_PASSWORD
	}

	@Resource
	private Ch14MemberDao memberDao;

	// 회원가입을 처리하는 비즈니스 메소드(로직)
	public JoinResult join(Ch14Member member) {
		logger.info("실행");
		try {
			// 이미 가입된 아이디인지 확인
			// SELECT * FROM member WHERE mid=?
			Ch14Member dbMember = memberDao.selectByMid(member.getMid());

			// DB에 회원 정보를 저장
			if (dbMember == null) {
				memberDao.insert(member);
				return JoinResult.SUCCESS;
			} else {
				return JoinResult.DUPLICATED;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return JoinResult.FAIL;
		}
	}

	// 로그인을 처리하는 비즈니스 메소드(로직)
	public LoginResult login(Ch14Member member) {
		logger.info("실행");
		try {
			// 가입된 아이디인지 확인
			// SELECT * FROM member WHERE mid=?
			Ch14Member dbMember = memberDao.selectByMid(member.getMid());

			// 등록된 아이디가 없음
			if (dbMember == null) {
				return LoginResult.WRONG_ID;
			}
			// 비밀번호가 다름
			if (!dbMember.getMpassword().equals(member.getMpassword())) {
				return LoginResult.WRONG_PASSWORD;
			}
			
			return LoginResult.SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return LoginResult.FAIL;
		}
	}
}
