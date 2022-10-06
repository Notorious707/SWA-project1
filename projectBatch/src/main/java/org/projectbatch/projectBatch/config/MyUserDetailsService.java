package org.projectbatch.projectBatch.config;

import lombok.AllArgsConstructor;
import org.projectbatch.projectBatch.entity.User;
import org.projectbatch.projectBatch.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with username:"+username);
        }
        return new MyUserDetails(user);
    }
}
