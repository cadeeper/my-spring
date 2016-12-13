package indi.nut.myspring.test;

import indi.nut.myspring.ioc.beans.BeanPostProcessor;

/**
 * Created by nut on 2016/12/13.
 */
public class BeanInitializeLogger implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println("postProcessBeforeInitialization!");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println("postProcessAfterInitialization!");
        return bean;
    }
}
