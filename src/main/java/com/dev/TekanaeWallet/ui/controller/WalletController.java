package com.dev.TekanaeWallet.ui.controller;

import com.dev.TekanaeWallet.exeptions.TekanaEWalletException;
import com.dev.TekanaeWallet.io.entity.WalletEntity;
import com.dev.TekanaeWallet.service.WalletService;
import com.dev.TekanaeWallet.ui.model.request.WalletRequestModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "SwaggerApiDocumentation", description = "Tekana eWallet Swagger APIs Documentation !!!!")
@CrossOrigin
@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    WalletService walletService;

    @ApiOperation(value = "createWallet", response = Iterable.class, tags = "Create new wallet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created|OK"),
            @ApiResponse(code = 400, message = "bad request!!!"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<WalletEntity> createWallet(@RequestBody WalletRequestModel walletDetails) {

        // check if any of the required fields is empty
        if (walletDetails.getCustomerId().isEmpty())
            throw new TekanaEWalletException("Invalid data passed, please check your data.", 400);

        WalletEntity newWallet = walletService.createWallet(walletDetails);

        return new ResponseEntity<>(newWallet, HttpStatus.CREATED);
    }

    @ApiOperation(value = "getAllWallets", response = Iterable.class, tags = "Read all wallets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<WalletEntity>> getAllWallets() {

        List<WalletEntity> allWallets = walletService.getAllWallets();

        return new ResponseEntity<>(allWallets, HttpStatus.OK);
    }

    @ApiOperation(value = "getWalletsOfACustomer", response = Iterable.class, tags = "Read wallets of a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})

    @GetMapping(
            path = "/customer/{customerId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<WalletEntity>> getWalletsOfACustomer(@PathVariable String customerId) {

        List<WalletEntity> foundWalletsOfACustomer = walletService.getWalletsOfACustomer(customerId);

        return new ResponseEntity<>(foundWalletsOfACustomer, HttpStatus.OK);
    }
}
