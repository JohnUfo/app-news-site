package uz.muydinovs.appnewssite.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.muydinovs.appnewssite.entity.User;
import uz.muydinovs.appnewssite.exceptions.ResourceNotFoundException;
import uz.muydinovs.appnewssite.payload.ApiResponse;
import uz.muydinovs.appnewssite.payload.RegisterDto;
import uz.muydinovs.appnewssite.repository.RoleRepository;
import uz.muydinovs.appnewssite.repository.UserRepository;
import uz.muydinovs.appnewssite.utils.AppConstants;


@Component
public class AuthService implements UserDetailsService {

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;

    public ApiResponse registerUser(@Valid RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())) {
            return new ApiResponse("Passwords are not equal", false);
        }

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            new ApiResponse("User already exists", false);
        }

        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.user).orElseThrow(() -> new ResourceNotFoundException("Role", "name", AppConstants.user)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("User registered!", true);
    }

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
