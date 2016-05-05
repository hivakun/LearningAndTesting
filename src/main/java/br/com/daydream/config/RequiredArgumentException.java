package br.com.daydream.config;

/**
 *
 * @author Lucas
 */
public class RequiredArgumentException extends RuntimeException {

    public RequiredArgumentException(String message) {
        super(message);
    }
}

