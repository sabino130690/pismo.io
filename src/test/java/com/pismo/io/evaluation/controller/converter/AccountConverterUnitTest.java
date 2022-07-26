package com.pismo.io.evaluation.controller.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.pismo.io.evaluation.controller.converter.AccountConverter.toAccountEntity;
import static com.pismo.io.evaluation.controller.converter.AccountConverter.toAccountResponse;
import static com.pismo.io.evaluation.controller.entities.fixture.AccountFixture.buildAccount;
import static com.pismo.io.evaluation.controller.fixture.AccountRequestFixture.buildAccountRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests to {@link AccountConverter}
 */
public class AccountConverterUnitTest {

    @Test
    @DisplayName("Should grant request data to entity account conversion")
    public void testSuccessfulConvertToEntity() {
        final var accountRequest = buildAccountRequest();
        final var entityRequest = toAccountEntity(accountRequest);

        assertNotNull(entityRequest);
        assertEquals(accountRequest.getDocument_number(), entityRequest.getDocument());

    }

    @Test
    @DisplayName("Should grant entity to response account conversion")
    public void testSuccessfulConvertToResponse() {
        final var account = buildAccount();
        final var response = toAccountResponse(account);

        assertNotNull(response);
        assertEquals(account.getId(), response.getAccount_id());
        assertEquals(account.getDocument(), response.getDocument_number());
    }
}
