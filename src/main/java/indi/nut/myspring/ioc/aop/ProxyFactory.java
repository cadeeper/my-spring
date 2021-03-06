package indi.nut.myspring.ioc.aop;

/**
 * 代理工厂，保存一个AopProxyFactory,可以通过不同的AopProxyFactory来获取代理对象
 * Created by nut on 2016/12/14.
 */
public class ProxyFactory extends AdvisedSupport{

    private AopProxyFactory aopProxyFactory;

    public ProxyFactory(){
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    public ProxyFactory(AopProxyFactory aopProxyFactory){
        this.aopProxyFactory = aopProxyFactory;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }


    protected final AopProxy createAopProxy(){
        return this.getAopProxyFactory().createAopProxy(this);
    }


    public AopProxyFactory getAopProxyFactory() {
        return aopProxyFactory;
    }
}
