package miage.skillz.service;

import miage.skillz.entity.ERole;
import miage.skillz.entity.Role;
import miage.skillz.repository.RoleRepository;
import miage.skillz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> findByName(ERole role) {
        return roleRepository.findByName(role);
    }
}
