package uz.muydinovs.appnewssite.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import uz.muydinovs.appnewssite.payload.ApiResponse;
import uz.muydinovs.appnewssite.payload.UserDto;

@Service
public class UserService {
    public ApiResponse addUser(@Valid UserDto userDto) {
        return null;
    }
}
