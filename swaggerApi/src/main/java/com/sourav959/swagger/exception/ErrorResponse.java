package com.sourav959.swagger.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Error response for the exceptions.
 *
 * @author sourav959
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponse {

    private int status;

    private String message;

    private long timeStamp;

}
