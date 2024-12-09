package com.oneiro.loanapp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Messages.printWelcomeMessage();
        Scanner scanner = ConsoleScanner.getInstance();

        int option = 0;
        while (option != 3) {
            Messages.printOptionMenu();
            System.out.print("Option : ");
            option = scanner.nextInt();
            scanner.nextLine();  //  consume the left-over newline

            Processor processor = new Processor();
            switch (option) {
                case 1:
                    processor.processInput(1);
                    break;
                case 2:
                    processor.processInput(2);
                    break;
                case 3:
                    System.out.println("Thank you!\n");
                    break;
                default:
                    Messages.printUnsupportedOptionMessage();
                    break;
            }
        }
        ConsoleScanner.close();
    }
}