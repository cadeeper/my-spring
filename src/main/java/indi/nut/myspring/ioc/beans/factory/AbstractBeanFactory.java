package indi.nut.myspring.ioc.beans.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import indi.nut.myspring.ioc.beans.BeanDefinition;
import indi.nut.myspring.ioc.beans.BeanPostProcessor;

/**
 * 抽像bean工厂，定义了获取一个bean的过程，具体bean属性的注入交给子类实现
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
            bean = initializeBean(bean, name, beanDefinition);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    /**
     * 使用单例的方式初始化所有BEAN, 目前只实现了单例模式。
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception {
        for( String name : beanDefinitionNames ){
            getBean(name);
        }
    }

    /**
     * 注册一个bean定义
     * @param name
     * @param beanDefinition
     * @throws Exception
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
    }

    /**
     * 初始化bean，依次调用所有的beanPostProcessor对bean进行一些处理，如果要实现AOP主要在这一步，这里返回的bean已经是代理对象了！
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    protected Object initializeBean(Object bean, String beanName, BeanDefinition bdm) throws Exception{

        for( BeanPostProcessor beanPostProcessor : beanPostProcessors ){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }

        invokeInitMethods(bean, beanName, bdm);

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


    /**
     * 初始化方法, 只是简单调用实现了InitializingBean接口的bean的afterPropertiesSet()方法
     * @param bean
     * @param beanName
     */
    protected void invokeInitMethods(Object bean, String beanName, BeanDefinition bdm) throws Exception{

        boolean isInitializingBean = (bean instanceof InitializingBean);
        if(isInitializingBean){
            ((InitializingBean)bean).afterPropertiesSet();
        }

    }



    /**
     * 注入属性，由子类实现
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    protected abstract void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception;

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception{
        beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 根据类型获得bean集合
     * @param type
     * @return
     * @throws Exception
     */
    public List getBeansForType(Class type) throws Exception{
        List beans = new ArrayList<>();
        for(String beanDefinitionName : beanDefinitionNames){
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
}
