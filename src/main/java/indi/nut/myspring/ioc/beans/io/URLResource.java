package indi.nut.myspring.ioc.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Url资源，从Url中获取资源
 * Created by nut on 2016/12/13.
 */
public class URLResource implements Resource {

    private URL url;

    public URLResource(URL url) {
        this.url = url;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
