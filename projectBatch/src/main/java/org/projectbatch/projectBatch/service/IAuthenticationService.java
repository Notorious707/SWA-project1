package org.projectbatch.projectBatch.service;

import org.projectbatch.projectBatch.dto.LoginRequestDto;

public interface IAuthenticationService {
    String signin(LoginRequestDto loginRequestDto);
}
