package indi.nut.myspring.ioc.aop;

/**
 *pointcutAdvisor,pointcut通知器，继承自advisor,增加了一个获取pointcut的方法,可以通过pointcut来确定哪个方法需要进行代理
 * Created by nut on 2016/12/14.
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
