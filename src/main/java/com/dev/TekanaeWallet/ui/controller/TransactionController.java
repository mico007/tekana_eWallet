package com.dev.TekanaeWallet.ui.controller;

import com.dev.TekanaeWallet.TekanaEWalletApplication;
import com.dev.TekanaeWallet.exeptions.TekanaEWalletException;
import com.dev.TekanaeWallet.io.entity.TransactionEntity;
import com.dev.TekanaeWallet.service.TransactionService;
import com.dev.TekanaeWallet.ui.model.request.TransactionRequestModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "SwaggerApiDocumentation", description = "Tekana eWallet Swagger APIs Documentation !!!!")
@CrossOrigin
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @ApiOperation(value = "createNewTransaction", response = Iterable.class, tags = "Create new transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created|OK"),
            @ApiResponse(code = 400, message = "bad request!!!"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<TransactionEntity> createNewTransaction(@RequestBody TransactionRequestModel transactionDetails) {

        // check if any of the required fields is empty
        if (transactionDetails.getTransactionType().isEmpty() || transactionDetails.getAmount() == 0 || transactionDetails.getWalletId().isEmpty())
            throw new TekanaEWalletException("Invalid data passed, please check your data.", 400);

        // check if the transaction type is valid
        if (!transactionDetails.getTransactionType().matches("[12]+"))
            throw new TekanaEWalletException("Invalid transaction type passed, please check your data.", 400);

        TransactionEntity newTransaction = transactionService.createTransaction(transactionDetails);

        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @ApiOperation(value = "getAllTransactions", response = Iterable.class, tags = "Read all transactions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "bad request!!!"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})
    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<TransactionEntity>> getAllTransactions(@RequestParam(required = false) String transactionType) {

        List<TransactionEntity> retrievedTransactions = transactionService.getAllTransactions(transactionType);

        return new ResponseEntity<>(retrievedTransactions, HttpStatus.OK);

    }

    @ApiOperation(value = "getTransactionsOfACustomer", response = Iterable.class, tags = "Read transactions of a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "bad request!!!"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})

    @GetMapping(path = "/customer/{walletId}", produces = {"application/json"})
    public ResponseEntity<List<TransactionEntity>> getTransactionsOfACustomer(@PathVariable String walletId, @RequestParam(required = false) String transactionType) {

        List<TransactionEntity> foundTransactionsOfACustomer = transactionService.getTransactionsOfACustomer(walletId, transactionType);

        return new ResponseEntity<>(foundTransactionsOfACustomer, HttpStatus.OK);

    }
}
