package com.example.familyhelpuae.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.familyhelpuae.security.MyUserDetailsService;


@Configuration
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;


    //Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	http.csrf(customizer -> customizer.disable());
    	
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register", "/login.html", "/register.html", "/css/**", "/js/**", "/images/**", "/error").permitAll()
                .anyRequest().authenticated()
        );
        
        http.formLogin(form -> form
        	       .loginPage("/login.html")
        	       .loginProcessingUrl("/login")
        	       .defaultSuccessUrl("/dashboard.html", true)
        	       .permitAll()
        );
        
        http.logout(logout -> logout
        	       .logoutUrl("/logout")
        	       .logoutSuccessUrl("/login.html")
        	       .permitAll()
        );
        
        http.httpBasic(Customizer.withDefaults());
        	
        return http.build();
    }

    
    //Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder( passwordEncoder() );
        return provider;
    }

    //Bcrypt Hashing
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
    
    
}
