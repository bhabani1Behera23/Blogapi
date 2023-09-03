package com.BlogApi.BlogApi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainClass {
    public static void main(String[] args) {
        PasswordEncoder passwordencoder=new BCryptPasswordEncoder();
        System.out.println( passwordencoder.encode("test"));
    }
}
