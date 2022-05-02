package com.test.compulynx.controller;

import com.test.compulynx.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/balance/{customerId}")
    public Double getCustomerBalance(@PathVariable("customerId") String customerId) {
        System.out.println("Controller path variable " + customerId);
        return accountService.getCustomerBalance(customerId);
    }

    @PostMapping(path = "/debit/{customerId}", params = "amount")
    public Double debitCustomerAccount(@PathVariable("customerId") String customerId, @PathParam("amount") Double amount) {
        return accountService.debitCustomerAccount(customerId, amount);
    }

    @PostMapping(path = "/credit/{customerId}", params = "amount")
    public Double creditCustomerAccount(@PathVariable("customerId") String customerId, @PathParam("amount") Double amount) {
        return accountService.creditCustomerAccount(customerId, amount);
    }

    @PostMapping(path = "/transfer", params = {"sourceAcc", "destAcc", "amount"})
    public Double transferFunds(@PathParam("amount") Double amount, @PathParam("sourceAcc") String sourceAcc, @PathParam("destAcc") String destAcc) {
        System.out.printf("Source param %s Dest Param %s Amount %s", sourceAcc, destAcc, amount);
        return accountService.transferFunds(amount, sourceAcc, destAcc);
    }
}
