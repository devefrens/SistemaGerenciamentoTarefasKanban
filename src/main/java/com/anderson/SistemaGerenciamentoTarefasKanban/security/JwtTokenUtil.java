//package com.anderson.SistemaGerenciamentoTarefasKanban.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import java.util.Date;
//
//public class JwtTokenUtil {
//
//    private String secretKey = "123456789"; // Coloque uma chave secreta segura aqui
//
//    // Método para gerar o token
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Token válido por 1 hora
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
//    }
//
//    // Método para validar o token
//    public boolean validateToken(String token, String username) {
//        String usernameFromToken = getUsernameFromToken(token);
//        return (usernameFromToken.equals(username) && !isTokenExpired(token));
//    }
//
//    // Método para extrair o nome de usuário do token
//    public String getUsernameFromToken(String token) {
//        return Jwts.parser()
//                .key()
//                .getClass().toString();
//    }
//
//    // Método para verificar se o token expirou
//    public boolean isTokenExpired(String token) {
//        Date expiration = (Date) Jwts.parser().key();
//
//        return expiration.before(new Date());
//    }
//}
