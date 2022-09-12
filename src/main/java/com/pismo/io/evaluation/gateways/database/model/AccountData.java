package com.pismo.io.evaluation.gateways.database.model;

import com.pismo.io.evaluation.entities.Account;
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

/**
 * Model representation of Account Data.
 */
@Entity
@Table(name = "tb_account")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_document", nullable = false)
    private String document;

    @Column(name = "account_amount_limit", nullable = false)
    private BigDecimal avalilableLimit;

    public Account toEntity() {
        return Account.builder()
                .id(this.id)
                .document(this.document)
                .limit(this.avalilableLimit)
                .build();
    }
}
