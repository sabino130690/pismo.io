package com.pismo.io.evaluation.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Transaction {

    private Long id;

    private Long accountId;

    private Long operationId;

    private BigDecimal amount;

    private LocalDateTime document;
}
