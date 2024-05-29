package ru.mikhailin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Ответ на запрос проверки компании")
public class ScoringCompanyResponse {
    @Schema(description = "Результат проверки", example = "false")
    private boolean result;
}
