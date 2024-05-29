package ru.mikhailin.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ScoringCompanyDmnParameters {
    INN("inn"),
    REGION("region"),
    CAPITAL("capital");

    private final String variableName;
}
