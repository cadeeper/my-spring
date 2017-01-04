package indi.nut.myspring.test;

import indi.nut.myspring.ioc.beans.BeanDefinition;
import indi.nut.myspring.ioc.beans.factory.AbstractAutowireCapableBeanFactory;
import indi.nut.myspring.ioc.beans.factory.DefaultListableBeanFactory;
import indi.nut.myspring.ioc.beans.factory.xml.XmlBeanDefinitionReader;
import indi.nut.myspring.ioc.beans.io.URLResourceLoader;
import org.junit.Test;

import java.util.Map;

/**
 * Created by nut on 2016/12/13.
 */
public class BeanFactoryTest {


    @Test
    public void testLoadBean() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new URLResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("myspring.xml");
        AbstractAutowireCapableBeanFactory beanFactory = new DefaultListableBeanFactory();
        for(Map.Entry<String,BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
