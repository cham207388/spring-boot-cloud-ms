package com.abc.ms.utils;

import java.text.MessageFormat;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
public class Constant {

    public static final String DEPARTMENT_SERVICE = "department-service";
    public static final String DEPARTMENT_PATH = "api/departments/{0}";
    public static final String ORGANIZATION_SERVICE = "organization-service";
    public static final String ORGANIZATION_PATH = "api/organizations/{0}";

    public static String deptPath(Object... objects) {
        return MessageFormat.format(DEPARTMENT_PATH, objects);
    }

    public static String orgPath(Object... objects) {
        return MessageFormat.format(ORGANIZATION_PATH, objects);
    }
}
