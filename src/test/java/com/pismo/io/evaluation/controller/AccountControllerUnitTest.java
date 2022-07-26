package com.pismo.io.evaluation.controller;

import com.pismo.io.evaluation.controller.dto.request.AccountRequest;
import com.pismo.io.evaluation.controller.handler.CustomExceptionHandler;
import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.exceptions.AccountAlreadyCreatedException;
import com.pismo.io.evaluation.exceptions.AccountNotFoundException;
import com.pismo.io.evaluation.usecases.CreateAccount;
import com.pismo.io.evaluation.usecases.FindAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {AccountController.class, ValidationAutoConfiguration.class, CustomExceptionHandler.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AccountControllerUnitTest extends AbstractControllerTest {

    @Autowired
    private AccountController accountController;

    @MockBean
    private FindAccount findAccount;

    @MockBean
    private CreateAccount createAccount;

    @Test
    @DisplayName("Should successfully get account information.")
    public void testSuccessfulGetAccount() throws Exception {

        when(findAccount.execute(any())).thenReturn(Account.builder().document("1234567890").id(1L).build());

        this.mockMvc.perform(get("/api/v1/evaluation/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Should fail get account information when it already exists.")
    public void testFailGetAccount() throws Exception {

        when(findAccount.execute(any())).thenThrow(new AccountNotFoundException());

        this.mockMvc.perform(get("/api/v1/evaluation/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should successfully post account creation.")
    public void testSuccessfulPostAccount() throws Exception {
        final var request = AccountRequest.builder().document_number("12345678910").build();

        when(createAccount.execute(any())).thenReturn(Account.builder().document("12345678910").id(1L).build());

        this.mockMvc.perform(post("/api/v1/evaluation/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Should fail post account creation when it already exists.")
    public void testFailPostAccount() throws Exception {
        final var request = AccountRequest.builder().document_number("12345678910").build();

        when(createAccount.execute(any())).thenThrow(new AccountAlreadyCreatedException());

        this.mockMvc.perform(post("/api/v1/evaluation/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Should fail post account creation when document is null.")
    public void testFailPostAccountWhenDocumentIsNull() throws Exception {
        final var request = AccountRequest.builder().build();

        this.mockMvc.perform(post("/api/v1/evaluation/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail post account creation when document is empty.")
    public void testFailPostAccountWhenDocumentIsEmpty() throws Exception {
        final var request = AccountRequest.builder().document_number("").build();

        this.mockMvc.perform(post("/api/v1/evaluation/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail post account creation when document has invalid format.")
    public void testFailPostAccountWhenDocumentIsOutOfPattern() throws Exception {
        final var request = AccountRequest.builder().document_number("zzzzzzzzz").build();

        this.mockMvc.perform(post("/api/v1/evaluation/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isBadRequest());
    }
}
