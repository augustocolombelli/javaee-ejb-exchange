package br.com.project.service.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ExchangeLogInterceptor {

	@AroundInvoke
	public Object intercept(InvocationContext invocationContext) throws Exception {
		System.out.println("[LOG] Start the interceptor");
		System.out.println("[LOG] Intercepted Method: " + invocationContext.getMethod().getName());
		System.out.println("[LOG] Intercepted Class: " + invocationContext.getTarget().getClass().getSimpleName());
		Object object = invocationContext.proceed();
		System.out.println("[LOG] Method was executed");
		return object;
	}

}
