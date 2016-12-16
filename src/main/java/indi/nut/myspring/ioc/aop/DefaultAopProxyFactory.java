package indi.nut.myspring.ioc.aop;

/**
 * Created by nut on 2016/12/14.
 */
public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(AdvisedSupport advisedSupport) throws RuntimeException {
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
