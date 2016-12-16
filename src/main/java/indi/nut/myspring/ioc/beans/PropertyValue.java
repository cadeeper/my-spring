package indi.nut.myspring.ioc.beans;

/**
 * 属性键值对
 * Created by nut on 2016/12/13.
 */
public class PropertyValue {

    private final String name;

    private final Object value;


    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
