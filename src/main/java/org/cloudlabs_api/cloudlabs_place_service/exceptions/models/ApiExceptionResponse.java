package org.cloudlabs_api.cloudlabs_place_service.exceptions.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ApiExceptionResponse {
    private HttpStatus status;
    private String type;
    private String debugMessage;
    private Date timestamp;
    private String title;
    private String message;
    private String detail;

    private ApiExceptionResponse() {
        this.timestamp = new Date();
    }

    public ApiExceptionResponse(HttpStatus status, Problem problem, String type, String debugMessage) {
        this();
        this.status = status;
        this.type = type;
        this.debugMessage = debugMessage;
        this.title = problem.getTitle();
        this.message = problem.getMessage();
        this.detail = problem.getDetail();
    }


}
