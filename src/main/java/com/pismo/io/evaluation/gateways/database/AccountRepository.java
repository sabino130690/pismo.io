package com.pismo.io.evaluation.gateways.database;

import com.pismo.io.evaluation.gateways.database.model.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountData, Long> {
}
