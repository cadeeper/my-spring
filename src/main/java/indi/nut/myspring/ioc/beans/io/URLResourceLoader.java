package indi.nut.myspring.ioc.beans.io;

import java.net.URL;

/**
 * Url资源加载器
 * Created by nut on 2016/12/13.
 */
public class URLResourceLoader implements ResourceLoader{

	@Override
	public Resource getResource(String location) {
		URL url = getClass().getClassLoader().getResource(location);
        return new URLResource(url);
	}

}
