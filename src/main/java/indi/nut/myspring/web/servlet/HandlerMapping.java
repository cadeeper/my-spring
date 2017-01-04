package indi.nut.myspring.web.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * 实现此类的接口需要定义一个请求到处理器(request -> handler)的映射
 * Created by nut on 2016/12/29.
 */
public interface HandlerMapping {

    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;

}
