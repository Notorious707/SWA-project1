package org.projectbatch.projectBatch.service;

import org.projectbatch.projectBatch.dto.AdminAssignDto;
import org.projectbatch.projectBatch.dto.RegisterRequestDto;
import org.projectbatch.projectBatch.dto.RegisterResponseDto;
import org.projectbatch.projectBatch.entity.User;

public interface IUserService {
    User findByUsername(String username);
    boolean signup(RegisterRequestDto registerRequestDto) throws Exception;
    boolean assignAdmin(AdminAssignDto adminAssignDto) throws Exception;
}
