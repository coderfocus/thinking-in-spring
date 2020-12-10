package com.coderfocus.thinking.in.spring.dependency.lookup;

import com.coderfocus.thinking.in.spring.dependency.lookup.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
* @Description: 通过 {@link ObjectProvider} 进行依赖查找
* @param:
* @return:
* @Author: songwenjie
* @Date: 2020-12-10
*/
public class ObjectProviderDemo {
    public static void main(String[] args) {
        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(ObjectProviderDemo.class);//不需要标记@Configuration
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找
        lookupByObjectProvider(applicationContext);
        //如果不存在，则返回一个默认的实例
        lookupIfAvailable(applicationContext);
        lookupUserIfAvailable(applicationContext);
        //ObjectProvider<T> extends Iterable<T>
        lookupByStreanOps(applicationContext);
        //关闭应用上下文
        applicationContext.close();
    }

    private static void lookupByStreanOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider =  applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider);
//        Iterable<String> iterable = objectProvider;
//        for (String item : iterable){
//            System.out.println(item);
//        }
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider =  applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getIfAvailable(()->"foo"));
    }

    private static void lookupUserIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider =  applicationContext.getBeanProvider(User.class);
        System.out.println(objectProvider.getIfAvailable(()->User.createUser()));
//        System.out.println(objectProvider.getIfAvailable(User::createUser));
    }

    @Bean //方法名就是 Bean 名称
    @Primary
    public String helloWorld(){
        return "hello world";
    }
    @Bean //方法名就是 Bean 名称
    public String foo(){
        return "foo!!!";
    }
    @Bean //方法名就是 Bean 名称
    public String bar(){
        return "bar!!!";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider =  applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
