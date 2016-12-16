package indi.nut.myspring.ioc.aop;

import org.aopalliance.aop.Advice;

/**
 * advisor，通知器，只有一个方法，返回一个advice，advice也就是一个方法拦截器，对目标方法进行增强
 * Created by nut on 2016/12/14.
 */
public interface Advisor {

    Advice getAdvice();
}
