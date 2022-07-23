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

@Entity
@Table(name = "tb_account")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", nullable = false)
    private Long id;

    @Column(name = "account_document", nullable = false)
    private String document;


    public Account toEntity() {
        return Account.builder()
                .id(this.id)
                .document(this.document)
                .build();
    }
}
