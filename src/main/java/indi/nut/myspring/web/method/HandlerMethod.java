package indi.nut.myspring.web.method;

import indi.nut.myspring.ioc.beans.factory.BeanFactory;
import indi.nut.myspring.ioc.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Method;

/**
 * Created by nut on 2016/12/29.
 */
public class HandlerMethod {


    private Object bean;

    private Method method;

    private Class<?> beanType;

    private AutowireCapableBeanFactory beanFactory;

    public HandlerMethod(Object bean, Method method){
        this.bean = bean;
        this.method = method;
        this.beanType = bean.getClass();
    }

    public HandlerMethod(String beanName, AutowireCapableBeanFactory beanFactory, Method method){
        this.bean = beanName;
        this.beanFactory = beanFactory;
        this.method = method;
    }

    public HandlerMethod(Object bean, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        this.bean = bean;
        this.method = bean.getClass().getMethod(methodName, parameterTypes);
        this.beanType = bean.getClass();
    }


    public Object getBean() {
        return this.bean;
    }

    public Method getMethod() {
        return this.method;
    }

    public Class<?> getBeanType() {
        return this.beanType;
    }
}
