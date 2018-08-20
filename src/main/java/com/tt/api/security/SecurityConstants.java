package com.tt.api.security;

public class SecurityConstants {
    public static final String SECRET = "SOME_PSEUDO_RANDOM_SECRET";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
}
