package com.brownfield.app.config;

import com.brownfield.app.service.UserServiceImpl;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private UserServiceImpl userService;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests((requests)->
                        requests.antMatchers("/api/v1/**").permitAll()
                        .antMatchers("/swagger-ui/**").permitAll()
                                .antMatchers("/js/**").permitAll()
                                .antMatchers("/register").permitAll()
                        .antMatchers("/").permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> {
                    form
                            .loginPage("/login")
                            .loginProcessingUrl("/signin")
                            .defaultSuccessUrl("/")
                            .permitAll();
                })
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/resources/**","/h2-console/**","/v2/api-docs"
                        ,"/configuration/ui", "/swagger-resources/**", "/configuration/**"
                        ,"/swagger-ui.html", "/webjars/**");
    }

//    @Bean
//    public UserDetailsService users() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("root"))
//                .roles("USER", "ADMIN")
//                .authorities("ROLE_ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }

}