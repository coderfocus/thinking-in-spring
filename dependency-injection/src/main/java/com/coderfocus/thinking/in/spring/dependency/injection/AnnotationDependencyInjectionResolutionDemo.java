package com.coderfocus.thinking.in.spring.dependency.injection;

import com.coderfocus.thinking.in.spring.dependency.injection.domain.UserHolder;
import com.coderfocus.thinking.in.spring.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.inject.Inject;
import java.util.Collection;

/**
* @Description: 基于注解方式的Constructor依赖注入示例
* @param: 
* @return:  
* @Author: songwenjie
* @Date: 2020-12-11 
*/
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
public class AnnotationDependencyInjectionResolutionDemo {
    //必须 required = true
    //实时注入 eager = true
    //通过类型 User.class
    //字段名称 user
    //是否首要 primary = true
    @Autowired
    private User user;

    @Inject
    private User injectUser;

//    @Autowired
//    private Collection<User> users;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();
        AnnotationDependencyInjectionResolutionDemo demo =  applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println("user: " + demo.user);
        System.out.println("injectUser: " + demo.injectUser);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
