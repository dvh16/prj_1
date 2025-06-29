package org.example.prj1.exception;


import org.springframework.http.HttpStatus;

public enum ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "User not found", "NOT_FOUND"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Username already exists", "BAD_REQUEST"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error", "INTERNAL_ERROR"),
    NOT_EXIST(HttpStatus.NOT_FOUND,"Todo not found" ,"NOT_FOUND" ),;

    ErrorCode(HttpStatus status, String msg, String code) {
        this.msg = msg;
        this.code = code;
        this.status = status;

    }

    private String msg;
    private String code;
    private HttpStatus status;

    public String getMsg() {
        return msg;

    }

    public String getCode() {
        return code;
    }


    public HttpStatus getStatus() {
        return status;
    }

}

