package com.coderfocus.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class ParentBeanFactoryDemo {
    public static HierarchicalBeanFactory createBeanFactory() {
        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(com.coderfocus.thinking.in.spring.dependency.lookup.ParentBeanFactoryDemo.class);//不需要标记@Configuration
        //启动应用上下文
        applicationContext.refresh();
        return applicationContext;
    }

    @Bean //方法名就是 Bean 名称
    public String foo() {
        return "foo!!!";
    }

    @Bean //方法名就是 Bean 名称
    public String bar() {
        return "bar!!!";
    }
}
