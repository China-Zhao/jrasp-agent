package com.jrasp.agent.exception;

// 被目标方法抛出
public class RaspException extends RuntimeException {

    public RaspException() {
        super();
    }

    public RaspException(String message) {
        super(message);
    }
}
