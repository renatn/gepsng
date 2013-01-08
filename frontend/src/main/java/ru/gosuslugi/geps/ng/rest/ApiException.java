package ru.gosuslugi.geps.ng.rest;

/**
 * User: rnasyrov
 * Date: 08.01.13
 * Time: 17:01
 */
public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
