package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class Ch15Aspect1Before {
	private static final Logger logger = LoggerFactory.getLogger(Ch15Aspect1Before.class);

// 해당 클래스 내부의 before로 시작하는 모든 메소드에 적용하겠다.
	@Before("execution(public * com.mycompany.webapp.controller.Ch15Controller.before*(..))")
	public void method() {
		logger.info("실행");
	}
}
