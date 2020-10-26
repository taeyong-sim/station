package com.station.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.station.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity // 1
@Configuration 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { 

  private final UserService userService; 

  @Bean 
  public PasswordEncoder passwordEncoder() { 
    return new BCryptPasswordEncoder();
  }
  
  @Override
  public void configure(WebSecurity web) { 
    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception { 

	  http
          .authorizeRequests() 
            .antMatchers("/login", "/signup", "/user").permitAll() 
            .antMatchers("/index").hasRole("ADMIN")
            .anyRequest().authenticated() 
        .and() 
          .formLogin() 
            .loginPage("/login") 
            .defaultSuccessUrl("/index") 
            
        .and()
          .logout() 
            .logoutSuccessUrl("/login")
	    .invalidateHttpSession(true)
    ;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder()); 
  }
}