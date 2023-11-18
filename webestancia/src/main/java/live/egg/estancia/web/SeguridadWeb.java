/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package live.egg.estancia.web;

import live.egg.estancia.web.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author pc
 */
@Configuration
@EnableWebSecurity
public class SeguridadWeb {
    
    @Autowired
    public UsuarioServicio usuarioServicio;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServicio)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public SecurityFilterChain secCadena(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                    .requestMatchers("/controlpanel/*")
                    .hasAnyRole("ADMIN", "ENCARGADO", "TITULAR")
                    .requestMatchers("/css/*", "/js/*", "/img/*", "/**")
                    .permitAll())
                .formLogin(formLoginCustomizer -> formLoginCustomizer
                    .loginPage("/usuario/login")
                    .loginProcessingUrl("/logincheck")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/usuario/")
                    .permitAll())
                .logout(logoutCustomizer -> logoutCustomizer
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/usuario/")
                    .permitAll())
                .exceptionHandling((noEntra) -> noEntra
                        .accessDeniedPage("/"))
                .csrf(csrfCustomizer -> csrfCustomizer
                .disable())
                .build();
        
    }
    
}
