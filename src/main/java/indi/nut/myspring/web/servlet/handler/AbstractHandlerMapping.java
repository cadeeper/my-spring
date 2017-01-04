package indi.nut.myspring.web.servlet.handler;

import indi.nut.myspring.ioc.context.support.ApplicationObjectSupport;
import indi.nut.myspring.util.PathMatcher;
import indi.nut.myspring.util.TruePathMatcher;
import indi.nut.myspring.web.servlet.HandlerExecutionChain;
import indi.nut.myspring.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nut on 2016/12/29.
 */
public abstract class AbstractHandlerMapping extends ApplicationObjectSupport implements HandlerMapping {


    private Object defaultHandler;

    private PathMatcher pathMatcher = new TruePathMatcher();

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        Object handler = getHandlerInternal(request);
        if(handler == null){
            handler = getDefaultHandler();
        }
        if(handler == null){
            return null;
        }

        HandlerExecutionChain executionChain = getHandlerExecutionChain(handler, request);
        return executionChain;
    }

    protected HandlerExecutionChain getHandlerExecutionChain(Object handler, HttpServletRequest request) {
        HandlerExecutionChain executionChain = new HandlerExecutionChain(handler);
        //TODO set interceptors
        return executionChain;
    }

    public Object getDefaultHandler() {
        return this.defaultHandler;
    }

    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }

    protected abstract Object getHandlerInternal(HttpServletRequest request);
}
