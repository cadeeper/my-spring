package indi.nut.myspring.test;

import indi.nut.myspring.ioc.context.ApplicationContext;
import indi.nut.myspring.ioc.context.ClasspathXmlApplicationContext;
import org.junit.Test;

/**
 * Created by nut on 2016/12/13.
 */
public class ApplicationContextTest {


    @Test
    public void testApplicationLoadBean() throws Exception {
        ApplicationContext ctx = new ClasspathXmlApplicationContext("myspring-processor.xml");
        HelloWorldService helloWorldService = (HelloWorldService) ctx.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
