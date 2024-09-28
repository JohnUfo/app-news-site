package uz.muydinovs.appnewssite.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.muydinovs.appnewssite.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
