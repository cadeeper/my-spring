package indi.nut.myspring.test.aop;

import indi.nut.myspring.ioc.aop.DefaultAdvisorAutoProxyCreator;
import indi.nut.myspring.ioc.context.ApplicationContext;
import indi.nut.myspring.ioc.context.ClasspathXmlApplicationContext;
import indi.nut.myspring.test.HelloWorldService;
import org.junit.Test;

/**
 * Created by nut on 2016/12/14.
 */
public class AopTest {


    @Test
    public void testAop() throws Exception {
        ApplicationContext ctx = new ClasspathXmlApplicationContext("myspring-aop.xml");
        HelloWorldService helloWorldService = (HelloWorldService) ctx.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
