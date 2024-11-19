//package com.anderson.SistemaGerenciamentoTarefasKanban.filter;
//
//import com.anderson.SistemaGerenciamentoTarefasKanban.security.JwtTokenUtil;
////import com.anderson.SistemaGerenciamentoTarefasKanban.util.JwtTokenUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@WebFilter
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private JwtTokenUtil jwtTokenUtil;
//    private AuthenticationManager authenticationManager;
//
//    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7); // Remover 'Bearer ' do token
//
//            if (jwtTokenUtil.validateToken(token, request.getUserPrincipal().getName())) {
//                String username = jwtTokenUtil.getUsernameFromToken(token);
//                UsernamePasswordAuthenticationToken authentication =
//                        new UsernamePasswordAuthenticationToken(username, null, null);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//
//    }
//
//}
//
