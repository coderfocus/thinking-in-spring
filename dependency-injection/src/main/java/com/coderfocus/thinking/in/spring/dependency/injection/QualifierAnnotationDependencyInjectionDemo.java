package com.coderfocus.thinking.in.spring.dependency.injection;

import com.coderfocus.thinking.in.spring.dependency.injection.annotation.GroupUser;
import com.coderfocus.thinking.in.spring.dependency.injection.config.UserConfig;
import com.coderfocus.thinking.in.spring.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;

import java.util.Collection;

/**
* @Description: 引申说明 ：
 * xml 注解 以及javaconfig的加载顺序
 * 综合目前看到的、经验证的修改方式，可实现注入所有 user beans：
 * 1. 示例代码基础上：当前 Demo 类作配置类 register 到容器，不依赖 XML，完全由配置类注入
 * 2. 示例代码基础上：依赖 XML，将当前 Demo 类中 @Bean 的声明都外移到另一配置类（如 MyConfig）中，再 register(MyConfig.class) 到容器
 * 3. 示例代码基础上：将 Demo 类中所有 @Bean 的方法都改为 static，static 会提早初始化Bean
* @param:
* @return:
* @Author: songwenjie
* @Date: 2020-12-11
*/
//@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
public class QualifierAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;//没有指定名称，默认@Primary

    @Autowired
    @Qualifier("user") // 指定 Bean 名称或 ID
    private User namedUser;

    @Autowired
    private Collection<User> allUsers; //全部User

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers; //分组User

    @Autowired
    @Qualifier("foo")
    private Collection<User> fooQualifierUsers; //foo分组User

    @Autowired
    @Qualifier("bar")
    private Collection<User> barQualifierUsers; //bar分组User

    @Autowired
    @GroupUser
    private Collection<User> groupUsers; //自定义分组User

    @Bean
    @Qualifier
    public User user1(){
        User user = new User();
        user.setId(1L);
        user.setName("user1");
        return user;
    }

    @Bean
    @Qualifier
    public User user2(){
        User user = new User();
        user.setId(2L);
        user.setName("user2");
        return user;
    }

    @Bean
    @Primary
    public User user3(){
        User user = new User();
        user.setId(3L);
        user.setName("user3");
        return user;
    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(0L);
        user.setName("user");
        return user;
    }

    @Bean
    @Qualifier("foo")
    public User user5(){
        User user = new User();
        user.setId(5L);
        user.setName("user5");
        return user;
    }

    @Bean
    @Qualifier("bar")
    public User user6(){
        User user = new User();
        user.setId(6L);
        user.setName("user6");
        return user;
    }

    @Bean
    @Qualifier("bar")
    public User user9(){
        User user = new User();
        user.setId(9L);
        user.setName("user9");
        return user;
    }

    @Bean
    @GroupUser
    public User groupUser1(){
        User user = new User();
        user.setId(7L);
        user.setName("groupUser1");
        return user;
    }
    @Bean
    @GroupUser
    public User groupUser2(){
        User user = new User();
        user.setId(8L);
        user.setName("groupUser2");
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        // 期待输出 superUser Bean
        System.out.println("demo.user = " + demo.user);
        // 期待输出 user Bean
        System.out.println("demo.namedUser = " + demo.namedUser);


        System.out.println("demo.allUsers = " + demo.allUsers);
        System.out.println("demo.qualifierUsers = " + demo.qualifierUsers);

        System.out.println("demo.fooQualifierUsers = " + demo.fooQualifierUsers);
        System.out.println("demo.barQualifierUsers = " + demo.barQualifierUsers);

        System.out.println("demo.groupUsers = " + demo.groupUsers);

        applicationContext.close();
    }
}
