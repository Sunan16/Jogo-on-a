package com.jogonca.api_backend.utils;

import java.util.UUID;
import java.util.regex.Pattern;

import com.jogonca.api_backend.exceptions.RequestErrorException;

public class Utils {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isNumeric(String s) {
        return s.matches("\\d");
    }

    public static Long toLong(String s) {
        if (!isNumeric(s)) {
            throw new RequestErrorException("Coloque um valor inteiro positivo!");
        }
        return Long.parseLong(s);
    }

    public static String generateRecoveryToken() {
        return UUID.randomUUID().toString();
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false; // E-mails vazios ou nulos são considerados inválidos
        }
        return EMAIL_PATTERN.matcher(email).matches(); // Retorna true se o e-mail for válido
    }

}
