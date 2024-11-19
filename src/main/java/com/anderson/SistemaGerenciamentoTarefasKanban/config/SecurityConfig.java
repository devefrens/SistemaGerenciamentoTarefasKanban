//package com.anderson.SistemaGerenciamentoTarefasKanban.config;
//
//import com.anderson.SistemaGerenciamentoTarefasKanban.filter.JwtAuthenticationFilter;
//import com.anderson.SistemaGerenciamentoTarefasKanban.security.JwtTokenUtil;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//
//    private JwtTokenUtil jwtTokenUtil;
//
//    public SecurityConfig(JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
////    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf(httpSecurityCsrfConfigurer -> {
//                    httpSecurityCsrfConfigurer.disable();
//                })
//                .authorizeRequests()
////                .antMatchers("/auth/login").permitAll() // Permite o login sem autenticação
//                .anyRequest().authenticated() // Exige autenticação para qualquer outro endpoint
//                .and()
//                .addFilter(new JwtAuthenticationFilter(jwtTokenUtil, null)); // Adiciona o filtro para verificar o JWT
//    }
//
////    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication() // Para fins de exemplo, usamos a autenticação em memória
//                .withUser("usuario_exemplo")
//                .password("{noop}senha_exemplo")
//                .roles("USER");
//    }
//
////    @Bean
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        AnnotationMetadata AuthenticationManager = null;
////        return super.setImportMetadata(AuthenticationManager);
////    }
//}
