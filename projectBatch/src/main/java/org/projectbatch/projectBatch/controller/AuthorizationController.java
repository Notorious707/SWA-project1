package org.projectbatch.projectBatch.controller;

import lombok.AllArgsConstructor;
import org.projectbatch.projectBatch.dto.*;
import org.projectbatch.projectBatch.service.AuthenticationService;
import org.projectbatch.projectBatch.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthorizationController {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationController.class);
    private AuthenticationService authenticationService;
    private UserService userService;
    private ResponseDto responseDto;

    @PostMapping("/signin")
    public ResponseDto signin(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) throws IOException {
        try {
            String token = authenticationService.signin(loginRequestDto);
            responseDto.setCode(0);
            responseDto.setMessage("Success");
            responseDto.setData(new LoginResponseDto(token));
            return responseDto;
        } catch (Exception exception) {
            responseDto.setCode(-1);
            responseDto.setData(null);
            responseDto.setMessage(exception.getMessage());
            log.error(exception.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return responseDto;
        }
    }

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody RegisterRequestDto registerRequestDto, HttpServletResponse response) throws Exception {
        try {
            boolean isRegistered = userService.signup(registerRequestDto);
            responseDto.setCode(0);
            responseDto.setMessage("Success");
            responseDto.setData(new RegisterResponseDto(isRegistered));
            return responseDto;
        } catch (Exception exception) {
            responseDto.setCode(-1);
            responseDto.setMessage(exception.getMessage());
            responseDto.setData(new RegisterResponseDto(false));
            log.error(exception.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return responseDto;
        }
    }
    @PostMapping("/assign-admin")
    public ResponseDto assignAdmin(@RequestBody AdminAssignDto adminAssignDto, HttpServletResponse response) throws Exception {
        try {
            boolean isAssigned = userService.assignAdmin(adminAssignDto);
            responseDto.setData(isAssigned);
            responseDto.setCode(0);
            responseDto.setMessage("Success");
            return responseDto;
        } catch (Exception exception) {
            responseDto.setCode(-1);
            responseDto.setMessage(exception.getMessage());
            responseDto.setData(false);
            log.error(exception.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return responseDto;
        }
    }
}
