package com.inhouse.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * フォーマット化されたコンソール出力
 */
public class ConsoleLogger {
    static final private DateTimeFormatter dateTimeFormatter = 
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    static final private String INFO = "INFO";
    static final private String ERROR = "ERROR";
    
    
    static public void info(String message) {
        System.out.println(new StringBuffer().append(timestampString()).append("\t").append(INFO).append("\t").append(message).toString());
    }

    static public void error(String message) {
        System.out.println(new StringBuffer().append(timestampString()).append("\t").append(ERROR).append("\t").append(message).toString());
    }


    static private String timestampString() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
