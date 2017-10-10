package com.marvelapi.web.exceptions;


public final class CharactersNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4592972565618625614L;

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
