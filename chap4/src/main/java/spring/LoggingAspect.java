package spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class LoggingAspect {
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable{
		//joinPoint : 실행의 순서를 저장하는 객체
		System.out.println("[LA]로그 시작");
		StopWatch sw = new StopWatch(); //
		sw.start();
		Object ret = joinPoint.proceed(); //핵심메서드를 호출
		sw.stop();
		System.out.println("[LA]메서드 실행시간:"+sw.getTotalTimeMillis()+"밀리초");
		return ret;
	}
}
