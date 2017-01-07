package indi.nut.myspring.ioc.beans.factory;

import java.util.List;

/**
 * Created by nut on 2017/1/3.
 */
public interface ListableBeanFactory extends BeanFactory {

    List getBeansForType(Class type) throws Exception;

    String[] getBeanNamesForType(Class<?> type);

}
