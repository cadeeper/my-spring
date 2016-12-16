package indi.nut.myspring.ioc.aop;

/**
 * AopProxy工厂，提供根据AdvisedSupport获得AopProxy方法
 * Created by nut on 2016/12/14.
 */
public interface AopProxyFactory {

    AopProxy createAopProxy(AdvisedSupport advisedSupport) throws RuntimeException;

}
