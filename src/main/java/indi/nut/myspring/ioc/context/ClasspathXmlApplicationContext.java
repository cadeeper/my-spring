package indi.nut.myspring.ioc.context;

import indi.nut.myspring.ioc.beans.BeanDefinition;
import indi.nut.myspring.ioc.beans.factory.AbstractAutowireCapableBeanFactory;
import indi.nut.myspring.ioc.beans.factory.DefaultListableBeanFactory;
import indi.nut.myspring.ioc.beans.factory.xml.XmlBeanDefinitionReader;
import indi.nut.myspring.ioc.beans.io.URLResourceLoader;

import java.util.Map;

/**
 * ClasspathXmlApplicationContext, 继承自AbstractApplicationContext,实现了从xml中加载类定义，并保存到内置的beanFactory中。<br/>
 * Created by nut on 2016/12/13.
 */
public class ClasspathXmlApplicationContext extends AbstractApplicationContext {

	private String configLocation;

	public ClasspathXmlApplicationContext(String configLocation) throws Exception {
		this(configLocation, new DefaultListableBeanFactory());
	}

	public ClasspathXmlApplicationContext(String configLocation, AbstractAutowireCapableBeanFactory beanFactory) throws Exception {
		super(beanFactory);
		this.configLocation = configLocation;
		refresh();
	}

	@Override
	protected void loadBeanDefinitions(AbstractAutowireCapableBeanFactory beanFactory) throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new URLResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
		for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
		}
	}
}
