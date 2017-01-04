package indi.nut.myspring.ioc.context;

/**
 * Created by nut on 2017/1/3.
 */
public interface ApplicationContextAware {

    void setApplicationContext(ApplicationContext applicationContext) throws Exception;

}
