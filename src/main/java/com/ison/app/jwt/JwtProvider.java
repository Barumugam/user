package com.ison.app.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ison.app.services.impl.UserPrinciple;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
 
@Component
public class JwtProvider {
 
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
 
    @Value("${app.jwtSecret}")
    private String jwtSecret;
 
    @Value("${app.jwtExpiration}")
    private int jwtExpiration;
 
    public String generateJwtToken(Authentication authentication) {
 
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
        			.setId((userPrincipal.getAutogenUsersId()))
                    .setSubject(userPrincipal.getEmployeeId())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
    }
 
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                      .setSigningKey(jwtSecret)
                      .parseClaimsJws(token)
                      .getBody().getSubject();
    }
 
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
    
    public Object[] validateJwtTokenObj(String authToken, HttpServletResponse response) throws IOException {
    	Object[] jwtTokenResult = new Object[2];
    	jwtTokenResult[1] = response;
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            jwtTokenResult[0] = true;
        } catch (SignatureException e) {
        	jwtTokenResult[0] = false;
        	((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid JWT signature -> Message: {}");
        	jwtTokenResult[1] = response;
            logger.error("Invalid JWT signature -> Message: {} ", e.getMessage());
        } catch (MalformedJwtException e) {
        	jwtTokenResult[0] = false;
        	((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid JWT token -> Message: {}");
        	jwtTokenResult[1] = response;
            logger.error("Invalid JWT token -> Message: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
        	jwtTokenResult[0] = false;
        	((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Expired JWT token -> Message: {}");
        	jwtTokenResult[1] = response;
            logger.error("Expired JWT token -> Message: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
        	jwtTokenResult[0] = false;
        	((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Unsupported JWT token -> Message: {}");
        	jwtTokenResult[1] = response;
            logger.error("Unsupported JWT token -> Message: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
        	jwtTokenResult[0] = false;
        	((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "JWT claims string is empty -> Message: {}");
        	jwtTokenResult[1] = response;
            logger.error("JWT claims string is empty -> Message: {}", e.getMessage());
        }catch (AccessDeniedException e) {
        	jwtTokenResult[0] = false;
        	((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Expired JWT token -> Message: {}");
        	jwtTokenResult[1] = response;
            logger.error("Expired JWT token -> Message: {}", e.getMessage());
        }
        
        return jwtTokenResult;
    }
}