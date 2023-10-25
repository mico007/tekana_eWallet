package com.dev.TekanaeWallet.exeptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

// This class is used to handle the system exceptions
@ControllerAdvice
public class TekanaEWalletExceptionHandler {

    // This method is used to handle custom exceptions
    @ExceptionHandler(value = {TekanaEWalletException.class})
    public ResponseEntity<Object> handleTekanaEWalletException(TekanaEWalletException ex, WebRequest req) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), ex.getStatusCode());
    }

    // This method is used to handle other exceptions
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest req) {

        System.out.println(ex.getMessage() == null ? "An error occurred while processing your request. Please try again later." : ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(new Date(), "An error occurred while processing your request. Please try again later.");

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
