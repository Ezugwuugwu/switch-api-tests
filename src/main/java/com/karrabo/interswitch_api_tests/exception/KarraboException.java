package com.karrabo.interswitch_api_tests.exception;

public class KarraboException extends Exception{

    public KarraboException(String message) {
        super(message);
    }

    public KarraboException(String message, Throwable cause) {
        super(message, cause);
    }

    public KarraboException(String message, Throwable cause, String additionalDetails) {
        super(message + ", " + additionalDetails, cause);
    }

    public KarraboException(Throwable cause) {
        super(cause);
    }

}
