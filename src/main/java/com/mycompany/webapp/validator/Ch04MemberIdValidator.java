package com.mycompany.webapp.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

public class Ch04MemberIdValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(Ch04MemberIdValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		logger.info("실행");
		boolean check = Ch04Member.class.isAssignableFrom(clazz);
		return check;
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.info("실행");
		Ch04Member member = (Ch04Member) target;

		// mid 검사
		if (member.getMid() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mid", "errors.mid.required");// mid에서 문제가 "errors.mid.required"로 인해 생김
		} else {
			if (member.getMid().length() < 4) {
				errors.rejectValue("mid", "errors.mid.minlength", new Object[] { 4 }, ""); // 키의 값을 넣기 위해서 객체를 생성해준다, 키가
																							// 없다면 에러 메세지를 직접 넣어둔다.
			}
		}
	}

}