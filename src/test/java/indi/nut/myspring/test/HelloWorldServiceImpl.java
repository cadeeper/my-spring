package indi.nut.myspring.test;

import indi.nut.myspring.ioc.beans.factory.InitializingBean;

/**
 * Created by nut on 2016/12/13.
 */
public class HelloWorldServiceImpl implements HelloWorldService , InitializingBean{

    private String text;



    @Override
    public void helloWorld() {
        System.out.println(text);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("in afterPropertiesSet, properties has been set, text=" + text);
    }
}
