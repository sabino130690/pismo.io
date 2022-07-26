package com.pismo.io.evaluation.controller.dto.request;

import com.pismo.io.evaluation.controller.validations.DocumentNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * Model representation of a Account request.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotEmpty(message = "{documentnumber.notempty}")
    @DocumentNumber
    private String document_number;

}