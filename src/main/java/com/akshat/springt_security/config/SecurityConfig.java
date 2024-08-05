package com.akshat.springt_security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// @EnableWebSecurity is used to enable Spring Security in our project.
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return provider;
    }


    @Bean
    // The @Bean annotation is used where we want to create an object of a class and store it in the Spring container.
    public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.
                csrf(customizer -> customizer.disable()).
                authorizeHttpRequests(request -> request.anyRequest().authenticated()).
                httpBasic(Customizer.withDefaults()).
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.csrf(customizer -> customizer.disable());
//        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    /*@Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User
                                .withDefaultPasswordEncoder()
                                .username("akshat")
                                .password("akshat")
                                .roles("USER")
                                .build();

        UserDetails admin = User
                                .withDefaultPasswordEncoder()
                                .username("admin")
                                .password("admin")
                                .roles("ADMIN")
                                .build();

        return new InMemoryUserDetailsManager(user,admin);
        // The InMemoryUserDetailsManager class is used to store the user details in the memory.

    }*/

}












// The securityFilterChain method is used to configure the security filter chain.
// The csrf method is used to disable the CSRF protection.
// The authorizeHttpRequests method is used to authorize all the HTTP requests.
// The anyRequest method is used to authorize any request. The authenticated method is used to authenticate the request.
// The httpBasic method is used to enable the HTTP Basic authentication. The withDefaults method is used to set the default values.
// The sessionManagement method is used to manage the session. The sessionCreationPolicy method is used to set the session creation policy.
// The STATELESS policy is used to create a stateless session. It means that the server does not store any session information.
