package indi.nut.myspring.ioc.aop;

import org.aopalliance.aop.Advice;

/**
 * Created by nut on 2016/12/14.
 */
public abstract class AbstractPointcutAdvisor implements PointcutAdvisor {

    private Advice advice;

    public AbstractPointcutAdvisor() {

    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
