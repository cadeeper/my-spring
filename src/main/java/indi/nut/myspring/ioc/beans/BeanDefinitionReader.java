package indi.nut.myspring.ioc.beans;

/**
 * Created by nut on 2016/12/13.
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;

}
