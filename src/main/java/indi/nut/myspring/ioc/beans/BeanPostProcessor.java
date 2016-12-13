package indi.nut.myspring.ioc.beans;

/**
 * Created by nut on 2016/12/13.
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
