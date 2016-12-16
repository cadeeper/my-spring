package indi.nut.myspring.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by nut on 2016/12/14.
 */
public class LogInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("LogInterceptor before method call");
        Object retVal = methodInvocation.proceed();
        System.out.println("LogInterceptor after method call");
        return retVal;
    }
}
