package uz.muydinovs.appnewssite.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.muydinovs.appnewssite.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);

    boolean existsByName(@NotBlank String name);
}
