package indi.nut.myspring.ioc.aop;

/**
 * Created by nut on 2016/12/14.
 */
public interface ClassFilter {

    boolean matches(Class targetClass);
}
