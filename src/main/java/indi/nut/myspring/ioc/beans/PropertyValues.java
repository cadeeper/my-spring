package indi.nut.myspring.ioc.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性键值对list，可以增强获取属性时的方法
 * Created by nut on 2016/12/13.
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();


    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void addPropertyValue(PropertyValue propertyValue){
        //TODO check duplicate

        this.propertyValueList.add(propertyValue);
    }
}
