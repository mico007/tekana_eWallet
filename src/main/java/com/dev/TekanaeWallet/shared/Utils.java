package com.dev.TekanaeWallet.shared;

import com.dev.TekanaeWallet.security.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    // This method is used to generate a unique secure public id of the customer records
    public String generateCustomerPublicId(int length) {
        return generateRandomString(length);
    }


    // This method is used to generate a unique secure public id of the wallet records
    public String generateWalletPublicId(int length) {
        return generateRandomString(length);
    }


    // This method is used to generate a unique secure public id of the transaction records
    public String generateTransactionPublicId(int length) {
        return generateRandomString(length);
    }

    // This method is used to generate a unique secure public id of the user records
    public String generateUserPublicId(int length) {
        return generateRandomString(length);
    }

    // This method is used to generate a unique secure string of the provided length
    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }


    // This method is used to generate a unique secure token for the user
    public String generateToken(String publicUserId) {

        String token = Jwts.builder()
                .setSubject(publicUserId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();

        return token;
    }

}
