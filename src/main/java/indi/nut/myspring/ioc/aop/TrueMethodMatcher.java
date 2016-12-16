package indi.nut.myspring.ioc.aop;

import java.lang.reflect.Method;

/**
 * Created by nut on 2016/12/14.
 */
public class TrueMethodMatcher implements MethodMatcher {
    @Override
    public boolean matches(Method method, Class targetClass) {
        return true;
    }
}
