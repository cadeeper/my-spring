package indi.nut.myspring.ioc.beans.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import indi.nut.myspring.ioc.beans.BeanDefinition;
import indi.nut.myspring.ioc.beans.BeanPostProcessor;

/**
 * Created by nut on 2016/12/13.
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<String> beanDefinitionNames = new ArrayList<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if(bean == null){
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    public void preInstantiateSingletons() throws Exception {
        for( String name : beanDefinitionNames ){
            getBean(name);
        }
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
    }

    protected Object initializeBean(Object bean, String beanName) throws Exception{

        for( BeanPostProcessor beanPostProcessor : beanPostProcessors ){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }

        //TODO initailze method

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors ){
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected abstract void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception;

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception{
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<Object> getBeansForType(Class type) throws Exception{
        List<Object> beans = new ArrayList<>();
        for(String beanDefinitionName : beanDefinitionNames){
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
}
