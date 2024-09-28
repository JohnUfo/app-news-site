package uz.muydinovs.appnewssite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.muydinovs.appnewssite.entity.Role;
import uz.muydinovs.appnewssite.entity.User;
import uz.muydinovs.appnewssite.entity.enums.Permission;
import uz.muydinovs.appnewssite.repository.RoleRepository;
import uz.muydinovs.appnewssite.repository.UserRepository;
import uz.muydinovs.appnewssite.utils.AppConstants;

import java.util.List;

import static uz.muydinovs.appnewssite.entity.enums.Permission.*;

@Component
public class DataLoader implements CommandLineRunner {

    RoleRepository roleRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Autowired
    @Lazy
    public DataLoader(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (initMode.equals("always")) {
            Permission[] permissions = Permission.values();
            Role admin = roleRepository.save(new Role(
                            AppConstants.admin,
                            List.of(permissions),
                            "System admin"
                    )
            );

            Role user = roleRepository.save(new Role(
                            AppConstants.user,
                            List.of(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),
                            "System user"
                    )
            );

            userRepository.save(new User("Umidjon Muydinov", "m1lymoe", passwordEncoder.encode("admin"), admin, true));
            userRepository.save(new User("Umidjon Tursunov", "m1lymoe2", passwordEncoder.encode("user"), user, true));
        }
    }
}
