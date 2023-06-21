package com.king.urban.security.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * token的异常
 *
 * @author liukai
 */
public class TokenException extends AccessDeniedException {

    public TokenException(String msg) {
        super(msg);
    }

    public TokenException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
