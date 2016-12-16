package indi.nut.myspring.ioc.aop;

/**
 * pointcut，包含了一个获得classFilter的方法和一个获得methodmatcher的方法，用于确定一个类的一个方法是否满足条件<br/>
 * Created by nut on 2016/12/14.
 */
public interface Pointcut{

    ClassFilter getClassFilter();

    MethodMatcher getmethodMatcher();
}
