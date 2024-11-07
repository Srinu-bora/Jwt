// // // // // package com.example.credentials.config;

// // // // // import org.springframework.context.annotation.Bean;
// // // // // import org.springframework.context.annotation.Configuration;
// // // // // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // // // // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // // // // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // // // // import org.springframework.security.crypto.password.PasswordEncoder;
// // // // // import org.springframework.security.web.SecurityFilterChain;

// // // // // @Configuration
// // // // // @EnableWebSecurity
// // // // // public class SecurityConfig {

// // // // //     @Bean
// // // // //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// // // // //         http
// // // // //             .csrf().disable() // Disable CSRF for testing; enable in production!
// // // // //             .authorizeHttpRequests(authorize -> authorize
// // // // //                 .requestMatchers("/api/users/register").permitAll() // Allow access to registration endpoint
// // // // //                 .anyRequest().authenticated() // All other requests require authentication
// // // // //             );
// // // // //         return http.build();
// // // // //     }

// // // // //     @Bean
// // // // //     public PasswordEncoder passwordEncoder() {
// // // // //         return new BCryptPasswordEncoder();
// // // // //     }
// // // // // }
// // package com.example.credentials.config;

// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.http.HttpMethod;
// // import org.springframework.security.authentication.AuthenticationManager;
// // import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.security.web.SecurityFilterChain;

// // @Configuration
// // @EnableWebSecurity
// // public class SecurityConfig {

// //     @Bean
// //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// //         http
// //             .csrf().disable() // Disable CSRF for testing; enable in production!
// //             .authorizeHttpRequests(authorize -> authorize
// //                 .requestMatchers("/api/users/register").permitAll() // Allow access to registration endpoint
// //                 .requestMatchers("/api/users/login").permitAll()
// //                 .requestMatchers("/api/companies/**").permitAll() 
// //                 .requestMatchers("/api/company/**").permitAll() // Allow access to login endpoint
// //                 .anyRequest().authenticated() // All other requests require authentication
// //             );
// //         return http.build();
// //     }

// //     @Bean
// //     public PasswordEncoder passwordEncoder() {
// //         return new BCryptPasswordEncoder();
// //     }
// //     // @Override
// //     // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// //     //     // Add your authentication logic if needed
// //     // }

// //     // @Bean
// //     // @Override
// //     // public AuthenticationManager authenticationManagerBean() throws Exception {
// //     //     return super.authenticationManagerBean();
// //     // }

// // }
// package com.example.credentials.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @SuppressWarnings("deprecation")
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//                 .authorizeRequests()
//                 .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
//                 .anyRequest().authenticated();
        
//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }


package com.example.credentials.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll() // Allow these endpoints
                .anyRequest().authenticated();  // All other endpoints require authentication
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
