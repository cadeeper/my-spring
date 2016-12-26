package indi.nut.myspring.ioc.beans.factory;

/**
 * 实现此接口的bean, 在属性全部初始化完成后，会调用afterPropertiesSet()方法，可以定制一些初始化操作
 * Created by nut on 2016/12/26.
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
