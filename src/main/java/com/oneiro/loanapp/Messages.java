package com.oneiro.loanapp;

public class Messages {
    public static void printWelcomeMessage() {
        System.out.println("\n********************************************\n"
                + "        LOAN INTEREST CALCULATOR\n"
                + "********************************************\n\n");
    }

    public static void printOptionMenu() {
        System.out.println("Please select an option to continue:\n"
                + "1. Calculate loan interest\n"
                + "2. See previous calculations\n"
                + "3. Exit\n");
    }

    public static void printUnsupportedOptionMessage() {
        System.out.println("\nThe option selected is not supported.\n"
                + "Please choose an option from the below menu.");
    }
}
