package com.brownfield.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/resources/**","/h2-console/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable();
//        http.authorizeHttpRequests((requests)->requests
//                        .requestMatchers("/").permitAll()
//                .requestMatchers("/register").permitAll()
//                .requestMatchers("/api/v1/user/*").permitAll()
//                        .requestMatchers("/api/v1/flight/*").permitAll()
//                .anyRequest().authenticated())
//                .formLogin((form) -> {
//                    form
//                            .loginPage("/login")
//                            .loginProcessingUrl("/api/v1/user/login")
//                            .defaultSuccessUrl("/")
//                            .permitAll();
//                })
//                .logout((logout) -> {
//                logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
//        });;
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.builder().username("admin").password(
                passwordEncoder().encode("root")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
