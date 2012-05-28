package com.w3vill.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	
	@Pointcut("execution(public * * (..))")
	private void anyPublicMethod(){}
	
	@Pointcut("within(com.w3villa.controller..*)")
	private void inController(){}
	
	@Pointcut("within(com.w3villa.daoImpl..*)")
	private void inDaoImpl(){}
	
	@Around("(anyPublicMethod() &&  inController()) || (anyPublicMethod() &&  inDaoImpl())")
	public Object loggingInController(ProceedingJoinPoint pjp){
		Object obj = null;
		long startTime = 0l;
		long endTime = 0l;
		System.out.println(pjp.getTarget().getClass().getName()+" : "+pjp.getSignature()+" entry.");
		try {
			startTime = System.currentTimeMillis();
			obj = pjp.proceed();
			endTime = System.currentTimeMillis();
		} catch (Throwable e) {
			System.out.println(pjp.getTarget().getClass().getName()+" : "+pjp.getSignature()+" Error occured.");
			e.printStackTrace();
		}
		System.out.println("Time taken to execute  "+pjp.getSignature()+" method :"+(endTime-startTime)+" milliseconds.");
		System.out.println(pjp.getTarget().getClass().getName()+" : "+pjp.getSignature()+" exit.");
		return obj;
	}

}
