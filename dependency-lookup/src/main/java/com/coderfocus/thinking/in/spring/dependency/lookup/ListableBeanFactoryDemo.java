package com.coderfocus.thinking.in.spring.dependency.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/** 
* @Description: 集合类型依赖查找示例
* @param: 
* @return:  
* @Author: songwenjie
* @Date: 2020-12-10 
*/ 
public class ListableBeanFactoryDemo {
    public static void main(String[] args) {
        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(ListableBeanFactoryDemo.class);//不需要标记@Configuration
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找
        lookupBeanNamesByListableBeanFactory(applicationContext);//推荐使用getBeanNamesForType
        lookupBeanByListableBeanFactory(applicationContext);//getBeansOfType可能会导致Bean提前初始化，导致Bean初始化不完全
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean //方法名就是 Bean 名称
    public String foo(){
        return "foo!!!";
    }

    @Bean //方法名就是 Bean 名称
    public String bar(){
        return "bar!!!";
    }


    private static void lookupBeanNamesByListableBeanFactory(AnnotationConfigApplicationContext applicationContext) {
        String[] beanNames = applicationContext.getBeanNamesForType(String.class);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }


    private static void lookupBeanByListableBeanFactory(AnnotationConfigApplicationContext applicationContext) {
        Map<String, String> beans = applicationContext.getBeansOfType(String.class);
        beans.forEach((k,v)->{
            System.out.println(v);
        });
    }
}
