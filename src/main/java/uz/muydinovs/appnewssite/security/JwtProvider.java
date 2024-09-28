package uz.muydinovs.appnewssite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.muydinovs.appnewssite.entity.*;

import java.util.Date;

@Component
public class JwtProvider {

    private final String secretKey = "BuTokenningMaxfiySoziHechKimBilmasim123456789012341234123421341241241234213412354rfgfdvcrtfbfdbfgvbfdbv"; // generates a secure key for HS512

    public String generateToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .claim("roles", role.getName())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            String email = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return email;
        } catch (Exception e) {
            return null;
        }
    }
}

