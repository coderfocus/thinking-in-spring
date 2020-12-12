package com.coderfocus.thinking.in.spring.dependency.injection;

import com.coderfocus.thinking.in.spring.dependency.injection.domain.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportResource;

/** 
* @Description: 基于Api的Constructor依赖注入示例 
* @param: 
* @return:  
* @Author: songwenjie
* @Date: 2020-12-11 
*/ 
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
public class ApiDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApiDependencyConstructorInjectionDemo.class);

        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
        applicationContext.registerBeanDefinition("userHolder",userHolderBeanDefinition);


        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找并且创建 Bean
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    /**
    * @Description: 构建 BeanDefinition
    * @param: []
    * @return: org.springframework.beans.factory.config.BeanDefinition
    * @Author: songwenjie
    * @Date: 2020-12-11
    */
    public static BeanDefinition createUserHolderBeanDefinition(){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addConstructorArgReference("superUser");
        return beanDefinitionBuilder.getBeanDefinition();
    }
}
