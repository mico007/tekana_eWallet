package com.dev.TekanaeWallet.ui.controller;

import com.dev.TekanaeWallet.exeptions.TekanaEWalletException;
import com.dev.TekanaeWallet.io.entity.CustomerEntity;
import com.dev.TekanaeWallet.service.CustomerService;
import com.dev.TekanaeWallet.shared.dto.CustomerDto;
import com.dev.TekanaeWallet.ui.model.request.CustomerRequestModel;
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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "customerRegistration", response = Iterable.class, tags = "Registration of a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created|OK"),
            @ApiResponse(code = 400, message = "bad request!!!"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CustomerEntity> customerRegistration(@RequestBody CustomerRequestModel customerDetails) {

        // check if any of the required fields is empty
        if (customerDetails.getFullName().isEmpty() || customerDetails.getPhone().isEmpty() || customerDetails.getSex().isEmpty() || customerDetails.getEmail().isEmpty() || customerDetails.getAge() == 0) {
            throw new TekanaEWalletException("Invalid data passed, please check your data.", 400);
        }

        //transfer customer details to customerEntity
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDetails, customerEntity);

        CustomerEntity newCustomerRecord = customerService.registerCustomer(customerEntity);


        return new ResponseEntity<>(newCustomerRecord, HttpStatus.CREATED);
    }

    @ApiOperation(value = "getAllCustomers", response = Iterable.class, tags = "Read all customers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {

        List<CustomerEntity> retrievedCustomers = customerService.getAllCustomers();

        return new ResponseEntity<>(retrievedCustomers, HttpStatus.OK);
    }

    @ApiOperation(value = "getCustomerByPublicId", response = Iterable.class, tags = "Read customer by public id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})
    @GetMapping(
            path = "/{customerPublicId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CustomerEntity> getCustomerByPublicId(@PathVariable String customerPublicId) {
        CustomerEntity foundCustomer = customerService.getCustomerByPublicId(customerPublicId);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }
}
