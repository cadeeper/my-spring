package indi.nut.myspring.ioc.aop;

import org.aopalliance.aop.Advice;

import java.awt.*;

/**
 * Created by nut on 2016/12/14.
 */
public class TruePointcut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return new TrueClassFilter();
    }

    @Override
    public MethodMatcher getmethodMatcher() {
        return new TrueMethodMatcher();
    }


}
