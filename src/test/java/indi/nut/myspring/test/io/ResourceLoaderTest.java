package indi.nut.myspring.test.io;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import indi.nut.myspring.ioc.beans.io.ResourceLoader;
import indi.nut.myspring.ioc.beans.io.URLResourceLoader;

/**
 * Created by nut on 2016/12/13.
 */
public class ResourceLoaderTest {

    @Test
    public void testResourceLoader() throws Exception {
        ResourceLoader resourceLoader = new URLResourceLoader();
        InputStream inputStream = resourceLoader.getResource("myspring.xml").getInputStream();
        byte[] buf = new byte[2048];
        inputStream.read(buf);
        System.out.print(new String(buf));
        Assert.assertNotNull(inputStream);
    }
}
