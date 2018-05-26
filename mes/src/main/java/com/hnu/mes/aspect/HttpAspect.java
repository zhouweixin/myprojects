package com.hnu.mes.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HttpAspect {

	/**
	 * 获取日志对象
	 */
	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

	/**
	 * 拦截
	 */
	@Pointcut("execution(public * com.hnu.mes.controller.*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		logger.info("\r\n\r\n================log start====================");

		// url
		logger.info("url = {}", request.getRequestURL());

		// method
		logger.info("method = {}", request.getMethod());

		// ip
		logger.info("ip = {}", request.getRemoteAddr());

		// 类方法
		logger.info("class_method = {}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

		// 参数
		logger.info("args = {}", joinPoint.getArgs());
		
		logger.info("\r\n================log end====================\r\n\r\n");
	}

	@After("log()")
	public void doAfter() {
		
	}

	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturning(Object object) {
		logger.info("response={}", object.toString());
	}
}

