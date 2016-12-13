package indi.nut.myspring.test;

import indi.nut.myspring.ioc.beans.BeanDefinition;
import indi.nut.myspring.ioc.beans.factory.AbstractBeanFactory;
import indi.nut.myspring.ioc.beans.factory.AutowireCapableBeanFactory;
import indi.nut.myspring.ioc.beans.factory.xml.XmlBeanDefinitionReader;
import indi.nut.myspring.ioc.beans.io.URLResourceLoader;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * Created by nut on 2016/12/13.
 */
public class BeanFactoryTest {


    @Test
    public void testLoadBean() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new URLResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("myspring.xml");
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for(Map.Entry<String,BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
