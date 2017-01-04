package indi.nut.myspring.ioc.context.support;

import indi.nut.myspring.ioc.context.ApplicationContext;
import indi.nut.myspring.ioc.context.ApplicationContextAware;

/**
 * Created by nut on 2017/1/3.
 */
public class ApplicationObjectSupport implements ApplicationContextAware{

    private ApplicationContext applicationContext;


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws Exception {
        this.applicationContext = applicationContext;
    }
}
