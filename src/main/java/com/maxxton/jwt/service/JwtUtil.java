package com.maxxton.jwt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

  private static final Long VALID_DURATION = 5L * 60 * 1000; // 5 MINUTES
  private static final String SECRET = "sojjwal";
  private static final String EMPLOYEE_ID = "employeeId";
  
  public static String generateToken(Long employeeId) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(EMPLOYEE_ID, employeeId);
    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + VALID_DURATION))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }
  
  public static boolean isTokenValid(String token) {
    Claims claims = getClaimsFromToken(token);
    Date tokenExpirationDate = claims == null ? null : claims.getExpiration();
    Date now = new Date();
    return tokenExpirationDate == null ? false : now.before(tokenExpirationDate);
  }
  
  public static Long getEmployeeId(String token) {
    Claims claims = getClaimsFromToken(token);
    Object empId = claims == null ? null : claims.get(EMPLOYEE_ID);
    return empId == null ? null : Long.valueOf(empId.toString());
  }
  
  private static Claims getClaimsFromToken(String token) {
    Claims claims;
    
    try {
      claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    } catch(Exception e) {
      claims = null;
    }
    
    return claims;
  }
}
