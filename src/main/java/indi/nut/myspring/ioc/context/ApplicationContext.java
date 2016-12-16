package indi.nut.myspring.ioc.context;

import indi.nut.myspring.ioc.beans.factory.BeanFactory;

/**
 * applicationContext接口，继承自beanFactory，实现类主要作用是加强了beanFactory，封装了资源加载、bean定义的加载的功能在里面。
 * Created by nut on 2016/12/13.
 */
public interface ApplicationContext extends BeanFactory {
}
