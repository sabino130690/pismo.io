package com.pismo.io.evaluation.gateways.database.model;

import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transaction")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", nullable = false)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "operation_id", nullable = false)
    private Long operationId;

    @Column(name = "operation_amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "account_document", nullable = false)
    private LocalDateTime document;


    public Transaction toEntity() {
        return Transaction.builder()
                .id(this.id)
                .document(this.document)
                .operationId(this.operationId)
                .amount(this.amount)
                .accountId(this.accountId)
                .build();
    }
}
