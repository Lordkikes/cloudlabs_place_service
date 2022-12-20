package org.cloudlabs_api.cloudlabs_place_service.exceptions.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

@Data
public class ApiExceptionResponseArguments {
    private HttpStatus status;
    private String type;
    private String debugMessage;
    private Date timestamp;
    private String title;
    private String message;
    private Map<String, String> detail;

    private ApiExceptionResponseArguments() {
        this.timestamp = new Date();
    }

    public ApiExceptionResponseArguments(HttpStatus status, Problem problem, String type, String debugMessage, Map<String, String> detail) {
        this();
        this.status = status;
        this.type = type;
        this.debugMessage = debugMessage;
        this.title = problem.getTitle();
        this.message = problem.getMessage();
        this.detail = detail;
    }


}
