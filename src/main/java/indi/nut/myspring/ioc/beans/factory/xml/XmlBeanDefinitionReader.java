package indi.nut.myspring.ioc.beans.factory.xml;

import indi.nut.myspring.ioc.beans.*;
import indi.nut.myspring.ioc.beans.io.ResourceLoader;
import indi.nut.myspring.ioc.beans.io.URLResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * Xml bean定义加载器，从xml中加载bean定义
 * Created by nut on 2016/12/13.
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {


    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        registerBeanDefinitions(document);
        inputStream.close();
    }

    protected void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root) {
        NodeList nodeList = root.getChildNodes();
        for( int i = 0 ; i < nodeList.getLength(); i++ ){
            Node node = nodeList.item(i);
            if( node instanceof Element ){
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    protected void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        BeanDefinition mbd = new BeanDefinition();
        mbd.setBeanClassName(className);
        processProperty(ele,mbd);
        getRegistry().put(name, mbd);
    }

    protected void processProperty(Element ele, BeanDefinition mbd) {
        NodeList propertyNodes = ele.getElementsByTagName("property");
        for(int i = 0 ; i < propertyNodes.getLength(); i++ ){
            Node node = propertyNodes.item(i);
            if(node instanceof Element){
                Element propertyElement = (Element) node;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if(value != null && value.length() > 0 ){
                    mbd.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                }else{
                    String ref = propertyElement.getAttribute("ref");
                    if(ref == null || ref.length() == 0){
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    mbd.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }


            }
        }
    }
}
