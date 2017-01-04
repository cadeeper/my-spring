package indi.nut.myspring.web.servlet;

/**
 * Created by nut on 2016/12/29.
 */
public class HandlerExecutionChain {

    private final Object handler;

    public HandlerExecutionChain(Object handler){
        this.handler = handler;
    }

    public Object getHandler() {
        return handler;
    }
}
