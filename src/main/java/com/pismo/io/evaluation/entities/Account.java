package com.pismo.io.evaluation.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Account {
    private Long id;
    private String document;
}
