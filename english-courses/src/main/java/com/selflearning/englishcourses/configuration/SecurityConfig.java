package com.selflearning.englishcourses.configuration;

import com.selflearning.englishcourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    private static final String[] PUBLIC_MATCHERS = {
            "/", "/login*", "/register*", "/dictionary", "/api/**", "/favicon.ico",
            "https://fonts.gstatic.com/**, https://fonts.googleapis.com/**"
    };

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers("/logout", "/default").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().hasAnyRole("USER", "ADMIN")

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/default")
                .failureUrl("/login?errors")
                .permitAll()

                .and()
                .rememberMe()
                .key("rememberMe")
                .rememberMeParameter("remember-me")

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("remember-me")

                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")

                .and()
                .cors().disable()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**");
    }

}
