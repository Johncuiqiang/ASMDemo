package com.zx.plugin

/**
 * Log utils
 *
 * @author John Wayne Kuang on 2019/11/20
 */
class LOGGER {

    private static boolean DEBUG = true

    static void info(String message) {
        if (DEBUG)
            println("(Wafers) " + message)
    }
}