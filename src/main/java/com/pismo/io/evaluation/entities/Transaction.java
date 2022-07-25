package com.pismo.io.evaluation.entities;

import com.pismo.io.evaluation.exceptions.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representation of transaction information.
 */
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Transaction {

    private Long id;

    private Long accountId;

    private OperationType operation;

    private BigDecimal amount;

    private LocalDateTime eventDatetime;
}
