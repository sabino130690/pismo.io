package com.pismo.io.evaluation.gateways.database;

import com.pismo.io.evaluation.gateways.database.model.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountData, Long> {
    @Query(value = "SELECT * FROM tb_account WHERE account_document =:account_document", nativeQuery = true)
    Optional<AccountData> findByDocument(@Param("account_document") String document);
}
