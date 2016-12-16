package indi.nut.myspring.ioc.aop;

/**
 * class filter 接口，确定一个类是否匹配一定的条件<br/>
 * Created by nut on 2016/12/14.
 */
public interface ClassFilter {

    boolean matches(Class targetClass);
}
