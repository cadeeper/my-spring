package indi.nut.myspring.ioc.beans.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import indi.nut.myspring.ioc.beans.BeanDefinition;
import indi.nut.myspring.ioc.beans.BeanPostProcessor;
import indi.nut.myspring.ioc.beans.BeanReference;
import indi.nut.myspring.ioc.beans.PropertyValue;
import indi.nut.myspring.ioc.beans.factory.config.AutowireCapableBeanFactory;

/**
 * 抽像bean工厂，定义了获取一个bean的过程，具体bean属性的注入交给子类实现
 * Created by nut on 2016/12/13.
 */
public abstract class AbstractAutowireCapableBeanFactory implements ListableBeanFactory, AutowireCapableBeanFactory {

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


    @Override
    public void applyBeanPropertyValues(Object existingBean, String beanName) throws Exception {
        BeanDefinition bdm = beanDefinitionMap.get(beanName);
        applyPropertyValues(existingBean, bdm);
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        //如果bean实现了BeanFacotryAware接口，则把beanFactory引用传到bean里面去。
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            Object value = propertyValue.getValue();
            String name = propertyValue.getName();

            if(value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + name.substring(0, 1).toUpperCase() + name.substring(1), value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(name);
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }


    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 根据类型获得bean集合
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public List getBeansForType(Class type) throws Exception{
        List beans = new ArrayList<>();
        for(String beanDefinitionName : beanDefinitionNames){
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return beanDefinitionNames.toArray(new String[beanDefinitionNames.size()]);
    }
}
