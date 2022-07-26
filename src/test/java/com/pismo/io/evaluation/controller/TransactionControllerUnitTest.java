package com.pismo.io.evaluation.controller;

import com.pismo.io.evaluation.controller.converter.TransactionConverter;
import com.pismo.io.evaluation.controller.dto.request.TransactionRequest;
import com.pismo.io.evaluation.controller.handler.CustomExceptionHandler;
import com.pismo.io.evaluation.exceptions.AccountNotFoundException;
import com.pismo.io.evaluation.usecases.CreateTransaction;
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

import java.math.BigDecimal;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {TransactionController.class, ValidationAutoConfiguration.class, CustomExceptionHandler.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TransactionControllerUnitTest extends AbstractControllerTest {

    @Autowired
    private TransactionController accountController;

    @MockBean
    private CreateTransaction createTransaction;

    @Test
    @DisplayName("Should successfully post transaction creation.")
    public void testSuccessfulPostTransaction() throws Exception {
        final var request = TransactionRequest.builder().account_id(1L).operation_type_id(1L).amount(new BigDecimal("2.00")).build();

        when(createTransaction.execute(any())).thenReturn(TransactionConverter.toTransactionEntity(request));

        this.mockMvc.perform(post("/api/v1/evaluation/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Should fail post transaction creation when account it already exists.")
    public void testFailPostTransaction() throws Exception {
        final var request = TransactionRequest.builder().account_id(22L).operation_type_id(1L).amount(new BigDecimal("2.00")).build();

        when(createTransaction.execute(any())).thenThrow(new AccountNotFoundException());

        this.mockMvc.perform(post("/api/v1/evaluation/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should fail post transaction creation without operation_type_id.")
    public void testFailPostTransactionAndOperationTypeNull() throws Exception {
        final var request = TransactionRequest.builder().account_id(1L).amount(new BigDecimal("2.00")).build();

        this.mockMvc.perform(post("/api/v1/evaluation/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail post transaction creation without account_id.")
    public void testFailPostTransactionAndAccountIdNull() throws Exception {
        final var request = TransactionRequest.builder().operation_type_id(1L).amount(new BigDecimal("2.00")).build();

        this.mockMvc.perform(post("/api/v1/evaluation/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail post transaction creation without amount.")
    public void testFailPostTransactionAndAmountNull() throws Exception {
        final var request = TransactionRequest.builder().account_id(1L).operation_type_id(1L).build();

        this.mockMvc.perform(post("/api/v1/evaluation/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail post transaction creation without amount.")
    public void testFailPostTransactionAndAmountMinimum() throws Exception {
        final var request = TransactionRequest.builder().account_id(1L).operation_type_id(1L).amount(new BigDecimal("0.0")).build();

        this.mockMvc.perform(post("/api/v1/evaluation/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail post transaction creation with invalid operation type.")
    public void testFailPostTransactionAndOperationTypeInvalid() throws Exception {
        final var request = TransactionRequest.builder().account_id(1L).operation_type_id(0L).build();

        this.mockMvc.perform(post("/api/v1/evaluation/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().isBadRequest());
    }

}
