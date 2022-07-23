package com.pismo.io.evaluation.controller.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.core.instrument.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Response body for exceptions.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    @Schema(description = "Path where the error occurred")
    private String path;
    @Schema(description = "Error status")
    private Integer status;

    @Schema(description = "Informs if the print stack is logged")
    private boolean printStack;

    @Schema(description = "Date and time of error")
    @Builder.Default
    private ZonedDateTime timestamp = ZonedDateTime.now();

    @Schema(description = "Error details")
    private ExceptionErrorDescriptionResponse error;

    @JsonIgnore
    public Collection<Tag> toTags() {
        return List.of(Tag.of("error", this.status.toString()));
    }
}

