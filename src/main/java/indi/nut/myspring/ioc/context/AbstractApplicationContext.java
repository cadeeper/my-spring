package indi.nut.myspring.ioc.context;

import indi.nut.myspring.ioc.beans.BeanPostProcessor;
import indi.nut.myspring.ioc.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * applicationContext的抽象实现，内部保存了一个beanFactory,getBean方法直接调用了beanFactory的getBean方法，核心方法refresh(),
 * 定义了bean定义加载，注册beanPostProcessor等一系列的初始化操作流程，具体bean定义加载方法loadBeanDefinitions()交由子类实现，
 * 由子类来实现从不同来源的资源加载bean定义。<br/>
 *
 * Created by nut on 2016/12/13.
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory abstractBeanFactory){
        this.beanFactory = abstractBeanFactory;
    }

    public void refresh() throws Exception{
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }

    protected void onRefresh() throws Exception{
        beanFactory.preInstantiateSingletons();
    }


    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List<Object> beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for(Object beanPostProcessor : beanPostProcessors){
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
