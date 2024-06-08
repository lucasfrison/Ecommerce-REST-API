package com.ecommerce.user.business.domain.exception;

public class UserBusinessException extends Exception {

    public static String USER_EMAIL_ALEADY_EXISTS = "O email informado ja foi cadastrado.";
    public static String INVALID_USER_EMAIL_OR_PASSWORD = "Email e/ou senha invalidos";

    public UserBusinessException(String errorMessage) {
        super(errorMessage);
    }

}
