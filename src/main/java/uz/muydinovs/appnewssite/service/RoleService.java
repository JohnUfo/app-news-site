package uz.muydinovs.appnewssite.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muydinovs.appnewssite.entity.Role;
import uz.muydinovs.appnewssite.payload.ApiResponse;
import uz.muydinovs.appnewssite.payload.RoleDto;
import uz.muydinovs.appnewssite.repository.RoleRepository;

@Service
public class RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ApiResponse addRole(@Valid RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName())) {
            return new ApiResponse("This role is already exists", false);
        }
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setPermissions(roleDto.getPermissionList());
        roleRepository.save(role);
        return new ApiResponse("Successfully added role", true);
    }
}
