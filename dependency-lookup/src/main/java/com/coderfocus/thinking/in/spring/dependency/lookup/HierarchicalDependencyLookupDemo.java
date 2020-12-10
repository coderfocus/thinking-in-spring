package com.coderfocus.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(HierarchicalDependencyLookupDemo.class);//不需要标记@Configuration
        //启动应用上下文
        applicationContext.refresh();

        //1.获取HierarchicalBeanFactory <- ConfigurableBeanFactory 可修改、可配置（setter方法）
        // <- ConfigurableListableBeanFactory <- DefaultListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前 BeanFactory 的 Parent BeanFactory: "+ beanFactory.getParentBeanFactory());

        HierarchicalBeanFactory parentBeanFactory = ParentBeanFactoryDemo.createBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前 BeanFactory 的 Parent BeanFactory: "+ beanFactory.getParentBeanFactory());

        disPlayLocalBean(beanFactory,"foo");
        disPlayLocalBean(parentBeanFactory,"foo");

        disPlayBean(beanFactory,"foo");
        disPlayBean(parentBeanFactory,"foo");

        //关闭应用上下文
        applicationContext.close();
    }

    /** 从当前 BeanFactory 查找,忽略父级上下文
     * ignoring beans defined in ancestor contexts
    * @Description:
    * @param: [beanFactory, beanName]
    * @return: void
    * @Author: songwenjie
    * @Date: 2020-12-10
    */
    private static void disPlayLocalBean(HierarchicalBeanFactory beanFactory,String beanName) {
        System.out.println("当前BeanFactory:"+ beanFactory +"是否包含 bean: "+ beanName +": "+ beanFactory.containsLocalBean(beanName));
    }


    /** AbstarctBeanFactory类中有实现。 先从当前 BeanFactory 查找，若没找到，再从父级 BeanFactory 查找
    * @Description:
    * @param: [beanFactory, beanName]
    * @return: void
    * @Author: songwenjie
    * @Date: 2020-12-10
    */
    private static void disPlayBean(HierarchicalBeanFactory beanFactory,String beanName) {
        System.out.println("当前BeanFactory:"+ beanFactory +"是否包含 bean: "+ beanName +": "+ beanFactory.containsBean(beanName));
    }

}


