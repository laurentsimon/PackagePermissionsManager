package com.ampaschal.google.apps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4ShellPractice {

    static Logger logger = LogManager.getLogger(Log4ShellPractice.class);

    public static void main(String[] args) {
        System.out.println("Main says, 'Hello, world.'");
        logger.error(args.length > 0 ? args[0] : "[no data provided to log]");
        System.out.println("Main is exiting.");
    }
}
