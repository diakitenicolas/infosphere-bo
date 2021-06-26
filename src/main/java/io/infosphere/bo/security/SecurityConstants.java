package io.infosphere.bo.security;

import java.util.Collections;
import java.util.Map;

public final class SecurityConstants {


    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "gendarmerie-app";
    public static final String TOKEN_AUDIENCE = TOKEN_ISSUER + "-secure-api";
    public static final long ONE_MONTH_MILLISECONDS = 2628000000l;
    public static final Map<String, Object> TOKEN_HEADER_PARAMS = Collections.singletonMap("typ", TOKEN_TYPE);


    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}