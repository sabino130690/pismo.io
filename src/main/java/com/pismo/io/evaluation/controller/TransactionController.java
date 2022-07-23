package com.pismo.io.evaluation.controller;

import com.pismo.io.evaluation.usecase.FindAccount;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/evaluation/transactions")
@Tag(name = "Transaction", description = "Transaction V1")
public class TransactionController {

    private final FindAccount findAccount;


}
