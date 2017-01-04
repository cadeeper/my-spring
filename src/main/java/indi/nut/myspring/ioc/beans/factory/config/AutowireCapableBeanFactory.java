package indi.nut.myspring.ioc.beans.factory.config;

import indi.nut.myspring.ioc.beans.factory.BeanFactory;

/**
 * Created by nut on 2017/1/3.
 */
public interface AutowireCapableBeanFactory extends BeanFactory{

    void applyBeanPropertyValues(Object existingBean, String beanName) throws Exception;
}
