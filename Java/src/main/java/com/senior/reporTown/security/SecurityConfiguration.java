package com.senior.reporTown.security;

import com.senior.reporTown.security.jwt.AuthEntryPointJwt;
import com.senior.reporTown.security.jwt.AuthTokenFilter;
import com.senior.reporTown.service.LogoutHandlerService;
import com.senior.reporTown.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.senior.reporTown.security.UserRole.*;
import static com.senior.reporTown.security.UserPermission.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final LogoutHandlerService logoutHandlerService;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder, UserService userService,
                                 AuthEntryPointJwt unauthorizedHandler, LogoutHandlerService logoutHandlerService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.logoutHandlerService = logoutHandlerService;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/register", "/login", "/").permitAll()
            .antMatchers("/banCitizen/**").hasRole(ADMIN.name())
            //.antMatchers(HttpMethod.POST, "/postReport").hasAuthority(REPORT_WRITE.getPermission())
            .antMatchers(HttpMethod.POST, "/registerOfficial").hasAuthority(OFFICIAL_WRITE.getPermission())
            .requestMatchers(new CronMatcher()).permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .cors()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf()
            .disable()
            .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                .key("somethingverysecured")
            .and()
            .logout()
                .addLogoutHandler(logoutHandlerService)
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
