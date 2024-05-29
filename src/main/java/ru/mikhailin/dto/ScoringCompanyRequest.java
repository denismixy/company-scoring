package ru.mikhailin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Schema(description = "Запрос на проверку компании")
public class ScoringCompanyRequest {
    @Schema(description = "ИНН", example = "7743013902")
    @NotBlank(message = "ИНН должен быть заполнен")
    private String inn;
    @Schema(description = "Код региона по КЛАДР", example = "24")
    @NotNull(message = "Код региона должен быть заполнен")
    private int region;
    @Schema(description = "Капитал компании", example = "5000000")
    @NotNull(message = "Капитал компании должен быть заполнен")
    private long capital;
}
