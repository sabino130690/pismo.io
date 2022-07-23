package com.pismo.io.evaluation.controller.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pismo.io.evaluation.exceptions.enums.ErrorCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Error description for exceptions.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionErrorDescriptionResponse {

    @Schema(description = "Error message")
    private String message;
    @Schema(description = "Error code")
    private ErrorCodeEnum code;
    @Schema(description = "Fields with error")
    private Map<String, List<String>> fields;

}

