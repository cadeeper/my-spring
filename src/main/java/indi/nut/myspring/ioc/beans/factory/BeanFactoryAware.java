package indi.nut.myspring.ioc.beans.factory;

/**
 * 扩展接口，实现此接口的bean会注入beanFactory实例，可以扩展IoC容器的功能
 * Created by nut on 2016/12/13.
 */
public interface BeanFactoryAware {

    void setBeanFactory(AbstractBeanFactory beanFactory) throws Exception;
}
