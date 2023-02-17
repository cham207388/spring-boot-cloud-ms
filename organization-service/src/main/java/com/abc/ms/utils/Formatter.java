package com.abc.ms.utils;

import java.text.MessageFormat;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
public class Formatter {

    private static final String ORGANIZATION_MESSAGE_ID = "No organization with id {0}";
    private static final String ORGANIZATION_MESSAGE_CODE = "No organization with code {0}";

    public static String idErrorMessage(Object... objects) {
        return MessageFormat.format(ORGANIZATION_MESSAGE_ID, objects);
    }

    public static String codeErrorMessage(Object... objects) {
        return MessageFormat.format(ORGANIZATION_MESSAGE_CODE, objects);
    }
}
