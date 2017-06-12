package cs544.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
/*import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;*/
import org.springframework.util.StopWatch;

@Aspect
public class BankSystemAdvice {

	@After("execution(* cs544.bank.dao.*.*(..))")
	public void logDao(JoinPoint joinpoint) throws Throwable {
		System.out.println("Logger Method called");
		System.out.println("Method Name=  " + joinpoint.getSignature().getName());
	}

	@Around("execution(* cs544.bank.service.*.*(..))")
	public Object methodTimer(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start(proceedingJoinPoint.getSignature().getName());
		Object obj = proceedingJoinPoint.proceed();
		stopWatch.stop();

		long timeTaken = stopWatch.getLastTaskTimeMillis();

		System.out.println("Method " + proceedingJoinPoint.getSignature().getName() + " In service Took " + timeTaken
				+ " Second To Finish");

		return obj;
	}

	@After("execution(* cs544.bank.logging.*.*(..))")
	public void traceLogger(JoinPoint joinPoint) {
		System.out.println("Tracing Method " + joinPoint.getSignature().getName() + " in logging package");
	}

	@After("execution(* cs544.bank.jms.*.*(..))")
	public void logJMSMessage(JoinPoint joinPoint) {
		System.out.println("Tracing method " + joinPoint.getSignature().getName() + " in JMS package");
	}
}
