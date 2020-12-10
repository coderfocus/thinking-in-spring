package com.coderfocus.thinking.in.spring.ioc.container;

import com.coderfocus.thinking.in.spring.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.awt.print.PrinterAbortException;
import java.util.Map;

public class AnnotationApplicationContextAsIocContainerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextAsIoCContainerDemo 作为配置类（Configuration Class）
        applicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnnotationApplicationContextAsIocContainerDemo.class);
        lookupCollectionByType(applicationContext);
    }
    /**
     * 通过 Java 注解的方式，定义了一个 Bean
     */
    @Bean
    public User foo() {
        User user = new User();
        user.setId(1L);
        user.setName("foo");
        return user;
    }
    @Bean
    public User bar() {
        User user = new User();
        user.setId(2L);
        user.setName("bar");
        return user;
    }

    public static void lookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 对象: " + users);
        }
    }
}
