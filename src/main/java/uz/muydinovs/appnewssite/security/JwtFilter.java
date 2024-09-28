package uz.muydinovs.appnewssite.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.muydinovs.appnewssite.service.AuthService;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    JwtProvider jwtProvider;
    AuthService authService;

    @Autowired
    @Lazy
    public JwtFilter(AuthService authService, JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("auth");
        System.out.println("Authorization Header: " + authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (jwtProvider.validateToken(token)) {
                String email = jwtProvider.getUsernameFromToken(token);
                System.out.println("Authenticated Username: " + email);
                UserDetails userDetails = authService.loadUserByUsername(email);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));

            }
        } else {
            System.out.println("No token found");
        }

        filterChain.doFilter(request, response);
    }
}
