package org.projectbatch.projectBatch.service;

import lombok.AllArgsConstructor;
import org.projectbatch.projectBatch.config.JwtHelper;
import org.projectbatch.projectBatch.dto.LoginRequestDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService implements  IAuthenticationService{
    private JwtHelper jwtHelper;
    private AuthenticationManager authenticationManager;

    @Override
    public String signin(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));
        String token=jwtHelper.generateToken(loginRequestDto.getUsername());
        return token;
    }
}
