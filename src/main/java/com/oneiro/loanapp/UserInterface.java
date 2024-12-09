package com.oneiro.loanapp;

import com.oneiro.loanapp.entity.Currency;
import com.oneiro.loanapp.entity.LoanParams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserInterface {

    public static LoanParams captureInput() {
        Scanner scanner = ConsoleScanner.getInstance();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate, endDate;

        System.out.println("Please input the following loan parameters:");
        System.out.print("Start date (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine();
        try {
            startDate = LocalDate.parse(startDateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid start date format");
        }

        System.out.print("End date (YYYY-MM-DD): ");
        String endDateStr = scanner.nextLine();
        try {
            endDate = LocalDate.parse(endDateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid end date format");
        }

        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date should be after start date");
        }

        System.out.print("Loan Amount: ");
        double amount = scanner.nextDouble();
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Loan amount should be greater than 0");
        }
        scanner.nextLine(); // Consume the leftover newline character

        System.out.print("Loan Currency: ");
        String cur = scanner.nextLine();
        Currency currency = Currency.fromString(cur);


        System.out.print("Base Interest Rate (%): ");
        double baseRate = scanner.nextDouble();
        if (baseRate <= 0.0) {
            throw new IllegalArgumentException("Base rate should be greater than 0");
        }
        scanner.nextLine(); // Consume the leftover newline character

        System.out.print("Margin (%): ");
        double margin = scanner.nextDouble();
        scanner.nextLine(); // Consume the leftover newline character

        return new LoanParams(startDate, endDate, amount, currency, baseRate, margin);
    }

    public static LoanParams captureUpdatedLoanParamInput(LoanParams oldLoanParams) {
        Scanner scanner = ConsoleScanner.getInstance();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LoanParams newLoanParams = new LoanParams();
        String input;

        System.out.println("Update start date (y/n) :");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            System.out.print("Start date (YYYY-MM-DD): ");
            String startDateStr = scanner.nextLine();
            try {
                LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
                newLoanParams.setStartDate(startDate);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid start date format");
            }
        } else {
            newLoanParams.setStartDate(oldLoanParams.getStartDate());
        }

        System.out.println("Update end date (y/n) :");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            LocalDate endDate;
            System.out.print("End date (YYYY-MM-DD): ");
            String endDateStr = scanner.nextLine();
            try {
                endDate = LocalDate.parse(endDateStr, dateFormatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid end date format");
            }
            if (!endDate.isAfter(newLoanParams.getStartDate())) {
                throw new IllegalArgumentException("End date should be after start date");
            }
        } else {
            if (!oldLoanParams.getEndDate().isAfter(newLoanParams.getStartDate())) {
                throw new IllegalArgumentException("End date should be after start date");
            }
            newLoanParams.setEndDate(oldLoanParams.getEndDate());
        }

        System.out.println("Update loan amount (y/n) :");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            System.out.print("Loan Amount: ");
            double amount = scanner.nextDouble();
            if (amount <= 0.0) {
                throw new IllegalArgumentException("Loan amount should be greater than 0");
            }
            newLoanParams.setAmount(amount);
            scanner.nextLine(); // Consume the leftover newline character
        } else {
            newLoanParams.setAmount(oldLoanParams.getAmount());
        }

        System.out.println("Update loan currency (y/n) :");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            System.out.print("Loan Currency: ");
            String cur = scanner.nextLine();
            newLoanParams.setCurrency(Currency.fromString(cur));
        } else {
            newLoanParams.setCurrency(oldLoanParams.getCurrency());
        }


        System.out.println("Update base interest rate (y/n) :");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            System.out.print("Base Interest Rate (%): ");
            double baseRate = scanner.nextDouble();
            if (baseRate <= 0.0) {
                throw new IllegalArgumentException("Base rate should be greater than 0");
            }
            newLoanParams.setBaseRate(baseRate);
            scanner.nextLine(); // Consume the leftover newline character
        } else {
            newLoanParams.setBaseRate(oldLoanParams.getBaseRate());
        }

        System.out.println("Update margin (y/n) :");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            System.out.print("Margin (%): ");
            newLoanParams.setMargin(scanner.nextDouble());
            scanner.nextLine(); // Consume the leftover newline character
        } else {
            newLoanParams.setMargin(oldLoanParams.getMargin());
        }

        return newLoanParams;
    }
}
