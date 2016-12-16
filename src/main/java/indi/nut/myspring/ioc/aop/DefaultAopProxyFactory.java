package indi.nut.myspring.ioc.aop;

/**
 * 默认AopProxyFactory,返回由JDK动态代理实现的AopProxy<br/>
 * Created by nut on 2016/12/14.
 */
public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(AdvisedSupport advisedSupport) throws RuntimeException {
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
