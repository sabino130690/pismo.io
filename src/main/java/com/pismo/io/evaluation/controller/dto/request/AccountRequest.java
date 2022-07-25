package com.pismo.io.evaluation.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Model representation of a Account request.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotEmpty
    private String document_number;

}