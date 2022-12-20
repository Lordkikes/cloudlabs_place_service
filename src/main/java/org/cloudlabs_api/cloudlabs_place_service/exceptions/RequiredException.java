package org.cloudlabs_api.cloudlabs_place_service.exceptions;

import lombok.Getter;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.Problem;

@Getter
public class RequiredException extends RuntimeException {
    private Problem problem;
    public RequiredException(Problem problem) {
        this.problem = problem;
    }
}
