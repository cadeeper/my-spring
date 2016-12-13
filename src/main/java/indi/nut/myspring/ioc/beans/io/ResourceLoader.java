package indi.nut.myspring.ioc.beans.io;

/**
 * Created by nut on 2016/12/13.
 */
public interface ResourceLoader {

    Resource getResource(String location) throws Exception;
}
