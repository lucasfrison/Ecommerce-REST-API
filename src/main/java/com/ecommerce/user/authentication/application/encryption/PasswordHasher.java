package com.ecommerce.user.authentication.application.encryption;

import io.quarkus.elytron.security.common.BcryptUtil;

public class PasswordHasher {

    private static final Integer hashIterator = 10;
    private static final byte[] hashSalt = "79fe08d75d35d818".getBytes();

    public static String encode(String password) {
        return BcryptUtil.bcryptHash(password, hashIterator, hashSalt);
    }

}
