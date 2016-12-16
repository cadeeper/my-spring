package indi.nut.myspring.ioc.beans;

/**
 * bean初始化接口，在第一次获取bean并初始化时，会获取容器中所有实现此接口的实现，并依次执行接口方法
 * Created by nut on 2016/12/13.
 */
public interface BeanPostProcessor {

    /**
     * 调用初始化方法之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    /**
     * 调用初始化方法之后执行
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
