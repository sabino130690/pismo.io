package com.pismo.io.evaluation.gateways.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_operation")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OperationData {

    @Id
    @Column(name = "operation_id", nullable = false)
    private Long id;

    @Column(name = "operation_description", nullable = false)
    private String description;



}
