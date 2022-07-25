package com.pismo.io.evaluation.controller.converter;

import com.pismo.io.evaluation.controller.dto.request.AccountRequest;
import com.pismo.io.evaluation.controller.dto.response.AccountResponse;
import com.pismo.io.evaluation.entities.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Converter responsible to convert Account information to entity and to controller data.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountConverter {

    /**
     * Builds Account entity into Account response controller object
     * @param account contains account entity data to be converted
     * @return {@code AccountResponse} returns account controller response entity
     */
    public static AccountResponse toAccountResponse(final Account account){
        return AccountResponse.builder()
                .account_id(account.getId())
                .document_number(account.getDocument())
                .build();
    }

    /**
     * Builds Account entity into Account response controller object
     * @param accountRequest contains account entity data to be converted
     * @return {@code Account} returns account entity
     */
    public static Account toAccountEntity(final AccountRequest accountRequest){
        return Account.builder()
                .document(accountRequest.getDocument_number())
                .build();
    }
}
