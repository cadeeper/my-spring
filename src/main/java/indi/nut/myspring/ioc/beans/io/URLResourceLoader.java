package indi.nut.myspring.ioc.beans.io;

import java.net.URL;

/**
 * Created by nut on 2016/12/13.
 */
public class URLResourceLoader implements ResourceLoader{

	@Override
	public Resource getResource(String location) {
		URL url = getClass().getClassLoader().getResource(location);
        return new URLResource(url);
	}

}
