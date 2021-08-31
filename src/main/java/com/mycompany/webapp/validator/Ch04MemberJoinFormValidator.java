package com.mycompany.webapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

public class Ch04MemberJoinFormValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(Ch04MemberJoinFormValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {// 유효성 검사를 할수있는지 없는지 확인
		// TODO Auto-generated method stub
		logger.info("실행");
		boolean check = Ch04Member.class.isAssignableFrom(clazz); // isAssignableFrom에 들어간 객체가 Ch04Member에 대입될수
																	// 있나(부모/자식)
		return check;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		logger.info("실행");
		Ch04Member member = (Ch04Member) target; // 강제 타입 변환

		// mid 검사
		if (member.getMid() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mid", "errors.mid.required");// mid에서 문제가 "errors.mid.required"로 인해 생김
		} else {
			if (member.getMid().length() < 4) {
				errors.rejectValue("mid", "errors.mid.minlength", new Object[] { 4 }, ""); // 키의 값을 넣기 위해서 객체를 생성해준다, 키가
																							// 없다면 에러 메세지를 직접 넣어둔다.
			}
		}

		// mpassword 검사
		if (member.getMpassword() == null || member.getMpassword().trim().equals("")) {
			errors.rejectValue("mpassword", "errors.mpassword.required");
		} else {
			if (member.getMpassword().length() < 8) {
				errors.rejectValue("mpassword", "errors.mpassword.minlength", new Object[] { 8 }, "");
			}
		}

		// memail 검사
		if (member.getMemail() == null || member.getMemail().trim().equals("")) {
			errors.rejectValue("memail", "errors.memail.required");
		} else {
			if (member.getMemail().length() < 8) {
				String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(member.getMemail());
				if (!matcher.matches()) {
					errors.rejectValue("memail", "errors.memail.invalid");
				}
			}
		}

		// mtel 검사
		if (member.getMtel() == null || member.getMtel().trim().equals("")) {
			errors.rejectValue("mtel", "errors.mtel.required");
		} else {
			if (member.getMtel().length() < 8) {
				String regex = "^\\d{3}-\\d{3,4}-\\d{4}$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(member.getMtel());
				if (!matcher.matches()) {
					errors.rejectValue("mtel", "errors.mtel.invalid");
				}
			}
		}
	}

}