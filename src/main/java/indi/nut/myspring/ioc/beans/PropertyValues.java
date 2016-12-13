package indi.nut.myspring.ioc.beans;

import java.util.ArrayList;
import java.util.List;

/**
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
