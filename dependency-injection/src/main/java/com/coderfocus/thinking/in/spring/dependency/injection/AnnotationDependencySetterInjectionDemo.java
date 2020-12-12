package com.coderfocus.thinking.in.spring.dependency.injection;

import com.coderfocus.thinking.in.spring.dependency.injection.domain.UserHolder;
import com.coderfocus.thinking.in.spring.ioc.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import sun.awt.SunHints;

import javax.annotation.Resource;

/**
* @Description: 基于注解方式的Setter依赖注入示例
* @param: 
* @return:  
* @Author: songwenjie
* @Date: 2020-12-11 
*/
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
public class AnnotationDependencySetterInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

//        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
//        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
//        // 加载 XML 资源，解析并且生成 BeanDefinition
//        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找并且创建 Bean
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }


}
