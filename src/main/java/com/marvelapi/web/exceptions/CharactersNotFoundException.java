package com.marvelapi.web.exceptions;


public final class CharactersNotFoundException extends RuntimeException {

    public CharactersNotFoundException() {
        super();
    }

    public CharactersNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CharactersNotFoundException(final String message) {
        super(message);
    }

    public CharactersNotFoundException(final Throwable cause) {
        super(cause);
    }

}
