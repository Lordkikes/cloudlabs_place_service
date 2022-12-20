package org.cloudlabs_api.cloudlabs_place_service.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Problem {
    private String title;
    private String message;
    private String detail;
}
