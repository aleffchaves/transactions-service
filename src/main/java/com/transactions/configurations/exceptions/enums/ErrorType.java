package com.transactions.configurations.exceptions.enums;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorType {

    ERROR_CODE_001,
    ERROR_CODE_002,
    ERROR_CODE_003,
    ERROR_CODE_004,
    ERROR_CODE_005;

    public String getMessage(final Locale messageLocale) {
        final var bundle = ResourceBundle.getBundle("i18n/exceptions", messageLocale);
        try {
            return new String(bundle.getString(this.name() + ".message")
                    .getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8.name());
        } catch (final UnsupportedEncodingException e) {
            return bundle.getString(this.name() + ".message");
        }
    }
}
