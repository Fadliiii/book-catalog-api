package com.ucorp.catalog.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.ucorp.catalog.dto.BookDetailResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LoggingAspect {
//	  public LoggingAspect() {
//	        System.out.println("🟢 LoggingAspect berhasil dimuat Spring!");
//	    }
	@Pointcut("execution(* com.ucorp.catalog.web.*.*(..))")
	private void restAPI() {}
	
	@Pointcut("within(com.ucorp.catalog.web.*)")
	private void withinPointcutExample() {
		
	}
	
	@Pointcut("args(com.ucorp.catalog.dto.PublisherCreateDTO)")
	private void argsPointercutExample() {}
	
	@Pointcut("@args(com.ucorp.catalog.annotaion.ThisLogArg)")
	private void argAnnotationsExample() {
		
	}
	
	@Pointcut("@annotation(com.ucorp.catalog.annotaion.LogThiMethod)")
	private void annotationPoincutExample() {
		
	}
	@Before("annotationPoincutExample()") 
	public void beforeExecutedLoggin() {
//        System.out.println("🔥 AOP log jalan: Before BookResource method executed!");
			log.info("This is log from Aspect Before Methode Executed");
	};
	
	@After("annotationPoincutExample()") 
	public void afterExecutedLogging() {
//        System.out.println("🔥 AOP log jalan: Before BookResource method executed!");
			log.info("This is log from Aspect After Method Executed");
	};
	
	@AfterReturning("annotationPoincutExample()") 
	public void afterReturnExecutedLogging() {
//        System.out.println("🔥 AOP log jalan: Before BookResource method executed!");
			log.info("This is log from Aspect After Return Method Executed");
	};
	
	@AfterThrowing("annotationPoincutExample()") 
	public void afterThrowingExecutedLogging() {
//        System.out.println("🔥 AOP log jalan: Before BookResource method executed!");
			log.info("This is log from Aspect After Throw Method Executed");
	};
	
	@Around("restAPI()")
	public Object processingTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		try {
			log.info("*************** start {}.{} *****************",joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName());
			stopWatch.start();
			return joinPoint.proceed();
		}finally {
			stopWatch.stop();
		    log.info("*************** finish {}.{} execution Time : {} *************** ",joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName(),
		    		stopWatch.getTotalTimeMillis());
		}
	}

}
