package indi.nut.myspring.ioc.beans;

/**
 * Bean定义加载器接口，所有加载器实现此接口
 * Created by nut on 2016/12/13.
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;

}
