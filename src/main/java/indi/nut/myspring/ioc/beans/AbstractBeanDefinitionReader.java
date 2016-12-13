package indi.nut.myspring.ioc.beans;

import indi.nut.myspring.ioc.beans.io.ResourceLoader;
import indi.nut.myspring.ioc.beans.io.URLResourceLoader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nut on 2016/12/13.
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private Map<String,BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader){
        this.registry = new ConcurrentHashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
