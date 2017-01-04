package indi.nut.myspring.util;

/**
 * Created by nut on 2017/1/3.
 */
public interface PathMatcher {

    boolean isPattern(String path);

    boolean match(String pattern, String path);
}
