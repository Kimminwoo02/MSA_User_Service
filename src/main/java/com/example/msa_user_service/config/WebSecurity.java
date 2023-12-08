package com.example.msa_user_service.config;

import com.example.msa_user_service.service.UserService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.io.IOException;
import java.util.function.Supplier;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Environment env;
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspect) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(new MvcRequestMatcher(introspect,"/users/**")).permitAll())
//                .requestMatchers(new MvcRequestMatcher(introspect,"/**")))
//                .addFilter(getAuthenticationFilter())
//                .addFilterAfter(getAuthenticationFilter())

                .build();
    }


//    @Bean
//    p AuthenticationFilter getAuthenticationFilter() throws IOException {
////         AuthenticationFilter authenticationFilter = new AuthenticationFilter(getAuthenticationFilter(),);
//////        authenticationFilter.setAuthenticationManager(authentication -> authentication.get);
////        return authenticationFilter;
//    }

    //select pwd from users where email = ?
    // db_pwd == input_pwd


    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }


}
