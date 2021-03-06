package indi.nut.myspring.ioc.beans;

/**
 * bean引用属性，包含名称和实例
 * Created by nut on 2016/12/13.
 */
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
