package org.cloudlabs_api.cloudlabs_place_service.exceptions;

import lombok.Getter;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.Problem;


@Getter
public class ConflictException extends RuntimeException {
    private Problem problem;
    public ConflictException(Problem problem) {
        this.problem = problem;
    }
}

