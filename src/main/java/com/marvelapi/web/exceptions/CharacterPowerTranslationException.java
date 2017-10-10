package com.marvelapi.web.exceptions;


/**
 * Exception for unsupported ISO-639-1 codes
 *
 */
public class CharacterPowerTranslationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -3746929043353775316L;

    public CharacterPowerTranslationException() {
        super();
    }

    public CharacterPowerTranslationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CharacterPowerTranslationException(final String message) {
        super(message);
    }

    public CharacterPowerTranslationException(final Throwable cause) {
        super(cause);
    }

}
