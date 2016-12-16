package indi.nut.myspring.ioc.aop;

/**
 * Created by nut on 2016/12/14.
 */
public class TrueClassFilter implements ClassFilter {
    @Override
    public boolean matches(Class targetClass) {
        return true;
    }
}
