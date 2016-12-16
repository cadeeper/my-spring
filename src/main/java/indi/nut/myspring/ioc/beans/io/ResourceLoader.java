package indi.nut.myspring.ioc.beans.io;

/**
 * 资源加载器，用于获取资源，所有资源加载器实现此接口
 * Created by nut on 2016/12/13.
 */
public interface ResourceLoader {

    Resource getResource(String location) throws Exception;
}
