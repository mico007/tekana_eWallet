package com.dev.TekanaeWallet.security;

import com.dev.TekanaeWallet.SpringApplicationContext;
import org.springframework.http.HttpMethod;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 3600000; // 1h
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String LOGIN_URL = "/users/login-user";
    public static final String REGISTER_CUSTOMER_URL = "/customers";
    public static final String CREATE_NEW_TRANSACTION_URL = "/transactions";
    public static final String CREATE_NEW_WALLET_URL = "/wallets";
    public static final String GET_CUSTOMER_BY_CUSTOMER_ID = "/customers/{customerPublicId}";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenSecret();
    }
}
