//package com.anderson.SistemaGerenciamentoTarefasKanban.controller;
//
//import com.anderson.SistemaGerenciamentoTarefasKanban.security.JwtTokenUtil;
////import com.anderson.SistemaGerenciamentoTarefasKanban.util.JwtTokenUtil
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
////    @Autowired
////    private JwtTokenUtil jwtTokenUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        // Aqui você pode fazer a autenticação real, por exemplo, consultar um banco de dados
//        if ("usuario_exemplo".equals(loginRequest.getUsername()) && "senha_exemplo".equals(loginRequest.getPassword())) {
//            String token = jwtTokenUtil.generateToken(loginRequest.getUsername());
//            return ResponseEntity.ok(new JwtResponse(token));
//        } else {
//            return ResponseEntity.status(401).body("Credenciais inválidas");
//        }
//    }
//
//    public static class LoginRequest {
//        private String username;
//        private String password;
//
//        // Getters e Setters
//
//        public String getUsername() {
//            return username;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//    }
//
//    // Classe para encapsular a resposta com o token
//    public static class JwtResponse {
//        private String token;
//
//        public JwtResponse(String token) {
//            this.token = token;
//        }
//
//        public String getToken() {
//            return token;
//        }
//
//        public void setToken(String token) {
//            this.token = token;
//        }
//    }
//}
