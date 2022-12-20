package org.cloudlabs_api.cloudlabs_place_service.exceptions;

import lombok.Getter;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.Problem;

@Getter
public class JsonProcessingException extends RuntimeException{
    private Problem problem;
    public JsonProcessingException(Problem problem) {
        this.problem = problem;
    }
}
