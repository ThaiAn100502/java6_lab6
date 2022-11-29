package com.fpoly.java6_lab6.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthConfig {
    @Autowired
    BCryptPasswordEncoder pe;

    // ma hoa mat khau
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // quan ly du lieu nguoi su dung
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(pe.encode("123")).roles("GUEST")
                .and()
                .withUser("user2").password(pe.encode("123")).roles("USER","GUEST")
                .and()
                .withUser("user3").password(pe.encode("123")).roles("ADMIN","USER","GUEST");
    }
    // phan quyen su dung va hinh thuc dang nhap
    protected void configure(HttpSecurity http) throws Exception{
        // CSRF, CORS
        http.csrf().disable().cors().disable();
        //phan quyen su dung
        http.authorizeRequests().antMatchers("home/index").permitAll()
                .anyRequest().authenticated();
    }
}
