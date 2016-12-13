package indi.nut.myspring.ioc.beans.factory;

/**
 * Created by nut on 2016/12/13.
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
