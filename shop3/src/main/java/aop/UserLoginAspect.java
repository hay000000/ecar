package aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import exception.LoginException;
import logic.User;

@Component
@Aspect
@Order(1)
public class UserLoginAspect {
	@Around("execution(* controller.User*.loginCheck*(..)) && args(..,session)")
	public Object userLoginCheck(ProceedingJoinPoint joinPoint,
				HttpSession session) throws Throwable{
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser == null) {
			throw new LoginException("[userlogin]�α��� �� �ŷ� �ϼ���","login.shop");
		}
		return joinPoint.proceed();
	}
	
	@Around("execution(* controller.User*.idcheck*(..)) && args(id,session,..)")
	public Object userIdCheck(ProceedingJoinPoint joinPoint,String id,HttpSession session) throws Throwable{
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser == null) {
			throw new LoginException("�α����ϼ���", "login.shop");
		}
		if(!loginUser.getUserid().equals("admin") && !loginUser.getUserid().equals(id)) {
			throw new LoginException("���θ� ��ȸ����", "main.shop");
		}
		return joinPoint.proceed();
	}
}
