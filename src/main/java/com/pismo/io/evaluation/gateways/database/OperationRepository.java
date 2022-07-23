package com.pismo.io.evaluation.gateways.database;

import com.pismo.io.evaluation.gateways.database.model.OperationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<OperationData, Long> {
}
