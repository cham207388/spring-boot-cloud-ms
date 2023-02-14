package com.abc.ms.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */
@NoArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    private String message;
    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
