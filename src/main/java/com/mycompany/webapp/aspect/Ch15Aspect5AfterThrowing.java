package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Ch15Aspect5AfterThrowing {
	private static final Logger logger = LoggerFactory.getLogger(Ch15Aspect5AfterThrowing.class);

	@AfterThrowing(pointcut = "execution(public * com.mycompany.webapp.controller.Ch15Controller.afterThrowing*(..))", throwing = "e")
	public void method(Throwable e) {
		logger.info("실행");
		logger.info("예외 메세지: " + e.getMessage());
	}
}
