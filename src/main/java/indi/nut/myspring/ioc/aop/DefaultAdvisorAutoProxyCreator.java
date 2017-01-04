package indi.nut.myspring.ioc.aop;

import indi.nut.myspring.ioc.beans.BeanPostProcessor;
import indi.nut.myspring.ioc.beans.factory.AbstractAutowireCapableBeanFactory;
import indi.nut.myspring.ioc.beans.factory.BeanFactoryAware;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * 代理生成类，实现了BeanPostProcessor和BeanFactoryAware,后者可以获取到beanFactory实例，实现前者可以在每个bean在初始化时调用，
 * 判断是否满足代理条件，满足则返回代理对象。<br/>
 * Created by nut on 2016/12/14.
 */
public class DefaultAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

	private AbstractAutowireCapableBeanFactory beanFactory;

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
		for (DefaultPointcutAdvisor advisor : defaultPointcutAdvisors) {
			if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
				ProxyFactory advisedSupport = new ProxyFactory();
				advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
				advisedSupport.setMethodMatcher(advisor.getPointcut().getmethodMatcher());
				advisedSupport
						.setTargetSource(new TargetSource(bean.getClass(), bean.getClass().getInterfaces(), bean));
				return advisedSupport.getProxy();
			}
		}
		return null;
	}

	@Override
	public void setBeanFactory(AbstractAutowireCapableBeanFactory beanFactory) throws Exception {
		this.beanFactory = beanFactory;
	}
}
