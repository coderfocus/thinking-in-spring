package com.coderfocus.thinking.in.spring.dependency.injection;

import com.coderfocus.thinking.in.spring.dependency.injection.domain.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
* @Description: 基于 Xml 方式的Setter依赖注入示例
* @param: 
* @return:  
* @Author: songwenjie
* @Date: 2020-12-11 
*/ 
public class XmlDependencySetterInjectionDemo {
    public static void main(String[] args) {
        //Spring BeanFactory 的默认实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:/META-INF/dependency-setter-injection.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        UserHolder userHolder =beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
