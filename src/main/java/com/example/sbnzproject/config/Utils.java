package com.example.sbnzproject.config;

import com.example.sbnzproject.models.Reader;

public class Utils {
    public static final String KIE_GROUP_ID = "com.sbnz";
    public static final String KIE_ARTIFACT_ID = "drools";
    public static final String KIE_VERSION = "0.0.1-SNAPSHOT";
    public static final long SCAN_INTERVAL = 10000;

    public static final String BOOKS_SESSION = "books-session";
    public static final String RECOMMENDATIONS_AGENDA = "recommendations";
    public static final String LOGIN_SESSION = "login-session";

    public static String getCategory(Reader r) {
        return String.format("%s", r.isMale() ? "male" : "female");
    }
}