package indi.nut.myspring.ioc.context.support;

import indi.nut.myspring.ioc.beans.BeanPostProcessor;
import indi.nut.myspring.ioc.context.ApplicationContext;
import indi.nut.myspring.ioc.context.ApplicationContextAware;

/**
 * Created by nut on 2017/1/3.
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

	private final ApplicationContext applicationContext;

	public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		invokeAwareInterfaces(bean);
		return bean;
	}

	private void invokeAwareInterfaces(Object bean) throws Exception {
		if (bean instanceof ApplicationContextAware) {
			((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
		}
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		return bean;
	}
}
