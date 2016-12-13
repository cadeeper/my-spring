package indi.nut.myspring.ioc.context;

import indi.nut.myspring.ioc.beans.BeanDefinition;
import indi.nut.myspring.ioc.beans.factory.AbstractBeanFactory;
import indi.nut.myspring.ioc.beans.factory.AutowireCapableBeanFactory;
import indi.nut.myspring.ioc.beans.factory.xml.XmlBeanDefinitionReader;
import indi.nut.myspring.ioc.beans.io.ResourceLoader;
import indi.nut.myspring.ioc.beans.io.URLResourceLoader;

import java.util.Map;

/**
 * Created by nut on 2016/12/13.
 */
public class ClasspathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClasspathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClasspathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new URLResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for(Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }
}
