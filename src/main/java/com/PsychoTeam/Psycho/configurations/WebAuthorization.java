package com.PsychoTeam.Psycho.configurations;


import com.PsychoTeam.Psycho.Utils.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/clients","/api/activateAccount/**","/api/login").permitAll()
                .antMatchers(HttpMethod.GET,"/api/post","/api/products/**",  "/api/tattoers").permitAll()
                .antMatchers("/web/productManager.html").hasAnyAuthority("ADMIN")
                .antMatchers("/web/**").permitAll()
                .antMatchers("/api/clients/current/**","/api/post/**","/api/purchase/**","/api/cart/current/**","/api/appointments").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers("/rest/**", "/h2-console", "/api/**").hasAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("CLIENT", "ADMIN");

        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login")
                .defaultSuccessUrl("/web/index.html",true)
                .failureHandler((request, response, exception) -> {
                    response.setStatus(900);
                });

        http.logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

//        http.exceptionHandling()
//                .authenticationEntryPoint(((request, response, authException) ->                {
//                    if(request.getRequestURI().contains("/web") && !request.getRequestURI().contains("login") && !request.getRequestURI().contains("shop")){
//                        response.sendRedirect("/web/401.html");
//                    }}))
//                .accessDeniedHandler(accessDeniedHandler());

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.csrf().disable();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.headers().frameOptions().disable();
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

}