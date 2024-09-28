package uz.muydinovs.appnewssite.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.muydinovs.appnewssite.payload.ApiResponse;
import uz.muydinovs.appnewssite.payload.RegisterDto;
import uz.muydinovs.appnewssite.payload.UserDto;
import uz.muydinovs.appnewssite.service.AuthService;
import uz.muydinovs.appnewssite.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
