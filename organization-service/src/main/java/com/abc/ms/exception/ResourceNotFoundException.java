package com.abc.ms.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
@NoArgsConstructor
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private String message;

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
