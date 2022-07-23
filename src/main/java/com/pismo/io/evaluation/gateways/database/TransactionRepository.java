package com.pismo.io.evaluation.gateways.database;

import com.pismo.io.evaluation.gateways.database.model.TransactionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionData, Long> {
}
