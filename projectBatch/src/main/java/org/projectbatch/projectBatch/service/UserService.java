package org.projectbatch.projectBatch.service;

import lombok.AllArgsConstructor;
import org.projectbatch.projectBatch.dto.AdminAssignDto;
import org.projectbatch.projectBatch.dto.RegisterRequestDto;
import org.projectbatch.projectBatch.entity.Role;
import org.projectbatch.projectBatch.entity.User;
import org.projectbatch.projectBatch.repository.RoleRepository;
import org.projectbatch.projectBatch.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean signup(RegisterRequestDto registerRequestDto) throws Exception {
        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            throw new Exception("User with this username already exists!");
        }
        User user = new User();
        Role role=roleRepository.findByName("ROLE_CLIENT").get();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.getRoles().add(role);
        userRepository.save(user);
        return userRepository.existsByUsername(user.getUsername());
    }

    @Override
    public boolean assignAdmin(AdminAssignDto adminAssignDto) throws Exception {
        Optional<User> user=userRepository.findById(adminAssignDto.getUserId());
        if(user.isEmpty()){
            throw new Exception("User with this id does not exist!");
        }
        Set<Role> roles= new HashSet<Role>();
        for(Role role: roleRepository.findAll()){
            roles.add(role);
        }
        User foundUser=user.get();
        foundUser.getRoles().clear();
        foundUser.setRoles(roles);
        userRepository.save(foundUser);
        return true;
    }
}
