package uz.muydinovs.appnewssite.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.muydinovs.appnewssite.entity.User;
import uz.muydinovs.appnewssite.payload.ApiResponse;
import uz.muydinovs.appnewssite.payload.LoginDto;
import uz.muydinovs.appnewssite.payload.RegisterDto;
import uz.muydinovs.appnewssite.security.JwtProvider;
import uz.muydinovs.appnewssite.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthService authService;
    AuthenticationManager authenticationManager;
    JwtProvider jwtProvider;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(token);
    }
}
