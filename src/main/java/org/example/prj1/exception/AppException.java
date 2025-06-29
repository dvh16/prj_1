package org.example.prj1.exception;


import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    private final ErrorCode errorCode;
    private final ErrorTodo errorTodo;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.errorTodo = null;
    }

    public AppException(ErrorTodo errorTodo) {
        super(errorTodo.getMsg());
        this.errorCode = null;
        this.errorTodo = errorTodo;
    }

    public HttpStatus getStatus() {
        return errorCode != null ? errorCode.getStatus() : errorTodo.getStatus();
    }

    public String getMsg() {
        return errorCode != null ? errorCode.getMsg() : errorTodo.getMsg();
    }

    public String getCode() {
        return errorCode != null ? errorCode.getCode() : errorTodo.getCode();
    }
}
