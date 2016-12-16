package indi.nut.myspring.ioc.aop;

import org.aopalliance.aop.Advice;

/**
 * Created by nut on 2016/12/14.
 */
public class DefaultPointcutAdvisor implements PointcutAdvisor{

    private Pointcut pointcut = new TruePointcut();

    private Advice advice;

    public DefaultPointcutAdvisor(){
        this.pointcut = new TruePointcut();
    }

    public DefaultPointcutAdvisor(Advice advice){
        this.pointcut = new TruePointcut();
        setAdvice(advice);
    }

    public DefaultPointcutAdvisor(Advice advice, Pointcut pointcut) {
        this.pointcut = pointcut != null ? pointcut : new TruePointcut();
        setAdvice(advice);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
