package indi.nut.myspring.ioc.beans.factory;

/**
 * bean工厂，从IoC容器中获取一个bean
 * Created by nut on 2016/12/13.
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;
}
