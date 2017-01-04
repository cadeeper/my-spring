package indi.nut.myspring.web.servlet.handler;

import indi.nut.myspring.ioc.beans.factory.InitializingBean;
import indi.nut.myspring.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by nut on 2016/12/29.
 */
public abstract class AbstractHandlerMethodMapping<T> extends AbstractHandlerMapping implements InitializingBean{


    @Override
    protected Object getHandlerInternal(HttpServletRequest request) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initHandlerMethods();
    }


    protected void initHandlerMethods(){

    }

    class MappingRegistry{

        private final Map<T, HandlerMethod> mappingLookup = new LinkedHashMap<>();

        private final Map<String,List<T>> urlLookup = new ConcurrentHashMap<>();

        private final Map<String,List<HandlerMethod>> nameLookup = new ConcurrentHashMap<>();

        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


        public List<HandlerMethod> getHandlerMethodsByMappingName(String mappingName){
            return this.nameLookup.get(mappingName);
        }

        public List<T> getMappingsByUrl(String urlPath){
            return this.urlLookup.get(urlPath);
        }

        public void register(T mapping, Object handler, Method method) throws Exception {
            HandlerMethod handlerMethod = createHandlerMethod(handler, method);
            this.mappingLookup.put(mapping, handlerMethod);

            List<String> directUrls = getDirectUrls(mapping);
            for(String url : directUrls){
                List<T> mappings = this.urlLookup.getOrDefault(url, new ArrayList<T>());
                mappings.add(mapping);
                urlLookup.put(url, mappings);
            }

        }

    }

    protected List<String> getDirectUrls(T mapping){
        List<String> urls = new ArrayList<>(1);
        //TODO pattern
        for(String url : getMappingPathPatterns(mapping)){
            if(!getPathMatcher().isPattern(url)){
                urls.add(url);
            }
        }
        return urls;
    }

    protected abstract Set<String> getMappingPathPatterns(T mapping);

    protected HandlerMethod createHandlerMethod(Object handler, Method method) throws Exception {
        HandlerMethod handlerMethod;
        if(handler instanceof String){
            String beanName = (String) handler;
            handlerMethod = new HandlerMethod(beanName,getApplicationContext().getAutoWireCapableBeanFactory(), method);
        }else{
            handlerMethod = new HandlerMethod(handler, method);
        }
        return handlerMethod;
    }
}
