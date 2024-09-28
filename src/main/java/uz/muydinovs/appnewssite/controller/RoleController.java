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
import uz.muydinovs.appnewssite.payload.RoleDto;
import uz.muydinovs.appnewssite.service.AuthService;
import uz.muydinovs.appnewssite.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public HttpEntity<?> registerUser(@Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
