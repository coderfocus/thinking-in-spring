package com.coderfocus.thinking.in.spring.dependency.injection.config;

import com.coderfocus.thinking.in.spring.ioc.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    @Qualifier
    public User configUser1(){
        User user = new User();
        user.setId(10L);
        user.setName("configUser1");
        return user;
    }

    @Bean
    @Qualifier
    public User configUser2(){
        User user = new User();
        user.setId(20L);
        user.setName("configUser2");
        return user;
    }
}
