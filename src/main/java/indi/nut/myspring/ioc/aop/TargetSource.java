package indi.nut.myspring.ioc.aop;

/**
 * Created by nut on 2016/12/14.
 */
public class TargetSource {

    private Class<?> targetClass;

    private Class<?>[] interfaces;

    private Object target;

    public TargetSource(Class<?> targetClass, Class<?>[] interfaces, Object target) {
        this.targetClass = targetClass;
        this.interfaces = interfaces;
        this.target = target;
    }


    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public Object getTarget() {
        return target;
    }
}
