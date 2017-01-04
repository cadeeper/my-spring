package indi.nut.myspring.util;

/**
 * Created by nut on 2017/1/3.
 */
public class TruePathMatcher implements PathMatcher{

    @Override
    public boolean isPattern(String path) {
        return (path.indexOf('*') != -1 || path.indexOf('?') != -1);
    }

    @Override
    public boolean match(String pattern, String path) {
        return true;
    }
}
