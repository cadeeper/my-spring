package indi.nut.myspring.ioc.aop;

import indi.nut.myspring.ioc.beans.BeanPostProcessor;
import indi.nut.myspring.ioc.beans.factory.AbstractBeanFactory;
import indi.nut.myspring.ioc.beans.factory.BeanFactory;
import indi.nut.myspring.ioc.beans.factory.BeanFactoryAware;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * Created by nut on 2016/12/14.
 */
public class DefaultAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

	private AbstractBeanFactory beanFactory;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {

		if (Advice.class.isAssignableFrom(bean.getClass()) || Pointcut.class.isAssignableFrom(bean.getClass())
				|| Advisor.class.isAssignableFrom(bean.getClass())
				|| MethodInterceptor.class.isAssignableFrom(bean.getClass())) {
			return bean;
		}

		List<DefaultPointcutAdvisor> defaultPointcutAdvisors = beanFactory
				.getBeansForType(DefaultPointcutAdvisor.class);
        for(DefaultPointcutAdvisor advisor : defaultPointcutAdvisors){
			if(advisor.getPointcut().getClassFilter().matches(bean.getClass())){
				ProxyFactory advisedSupport = new ProxyFactory();
				advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
				advisedSupport.setMethodMatcher(advisor.getPointcut().getmethodMatcher());
				advisedSupport.setTargetSource(new TargetSource(bean.getClass(),bean.getClass().getInterfaces(),bean));
				return advisedSupport.getProxy();
			}
        }
		return null;
	}

	@Override
	public void setBeanFactory(AbstractBeanFactory beanFactory) throws Exception {
		this.beanFactory = beanFactory;
}
}
