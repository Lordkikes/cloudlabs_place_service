package org.cloudlabs_api.cloudlabs_place_service.exceptions;

import lombok.Getter;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.Problem;


@Getter
public class CommunicationException extends RuntimeException {
    private Problem problem;
    public CommunicationException(Problem problem) {
        this.problem = problem;
    }
}

