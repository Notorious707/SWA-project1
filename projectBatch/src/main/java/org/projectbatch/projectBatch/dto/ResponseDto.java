package org.projectbatch.projectBatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private int code;
    private String message="Success";
    private T data;
}
