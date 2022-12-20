package org.cloudlabs_api.cloudlabs_place_service.exceptions;

import lombok.Getter;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.Problem;


@Getter
public class BadRequestException extends RuntimeException {
    private Problem problem;
    public BadRequestException(Problem problem) {
        this.problem = problem;
    }
}
