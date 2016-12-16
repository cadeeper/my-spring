package indi.nut.myspring.ioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口
 * Created by nut on 2016/12/13.
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
