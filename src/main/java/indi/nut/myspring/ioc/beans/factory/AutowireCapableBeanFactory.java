package indi.nut.myspring.ioc.beans.factory;

import indi.nut.myspring.ioc.beans.BeanDefinition;
import indi.nut.myspring.ioc.beans.BeanReference;
import indi.nut.myspring.ioc.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by nut on 2016/12/13.
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception{

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
}
