package com.pismo.io.evaluation.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Entity representation of account information.
 */
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Account {
    private Long id;
    private String document;
}
