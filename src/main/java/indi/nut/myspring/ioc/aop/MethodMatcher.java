package indi.nut.myspring.ioc.aop;

import java.lang.reflect.Method;

/**
 * method matcher, 确定一个方法是否匹配一定的规则<br/>
 * Created by nut on 2016/12/14.
 */
public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);
}
