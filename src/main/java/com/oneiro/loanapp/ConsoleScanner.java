package com.oneiro.loanapp;

import java.util.Scanner;

public class ConsoleScanner {

    private static Scanner scannerInstance = new Scanner(System.in);

    private ConsoleScanner() {
    }

    public static Scanner getInstance() {
        return scannerInstance;
    }

    public static void close() {
        if (scannerInstance != null) {
            scannerInstance.close();
            scannerInstance = null;
        }
    }
}
