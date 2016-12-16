package indi.nut.myspring.ioc.beans;

import indi.nut.myspring.ioc.beans.io.ResourceLoader;
import indi.nut.myspring.ioc.beans.io.URLResourceLoader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽像bean定义加载器，整合了资源加载器resourceLoader提供给子类
 * Created by nut on 2016/12/13.
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    /**
     * 保存所有bean定义，beanName->beanDefinition
     */
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
