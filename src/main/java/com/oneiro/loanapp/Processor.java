package com.oneiro.loanapp;

import com.oneiro.loanapp.entity.Currency;
import com.oneiro.loanapp.entity.LoanParams;
import com.oneiro.loanapp.entity.LoanResult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Processor {
    public void processInput(int option) {
        switch (option) {
            case 1:
                computeInterest();
                break;
            case 2:
                showHistory();
                break;
        }
    }

    private void computeInterest() {
        try {
            LoanParams loanParams = captureInput();
            LoanResult loanResult = InterestCalculator.calculateInterest(loanParams);
            System.out.println(loanResult.toFormattedString(loanParams.getCurrency()));
            loanParamHistory.addLoanParams(loanParams);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error occurred while calculating result. Please try again!\n");
        }
    }

    private void showHistory() {
        try {
            List<LoanParams> records = loanParamHistory.getLoanParamHistory();
            if (records.isEmpty()) {
                System.out.println("Sorry, no calculation history to display.\n");
                return;
            }

            for (int i = 0; i < records.size(); ++i) {
                System.out.println(i + 1 + ". " + records.get(i).toString() + '\n');
            }

            Scanner scanner = ConsoleScanner.getInstance();
            System.out.print("Do you want to re-run a historical calculation (y/n) :");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("y")) {
                System.out.print("Please choose the calculation to re-run: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                if (option > records.size()) {
                    System.out.println("Invalid option.");
                    return;
                }

                System.out.println("Selected calculation: \n"
                        + option + ". " + records.get(option - 1).toString());

                LoanParams loanParams = captureUpdatedLoanParamInput(records.get(option - 1));
                LoanResult loanResult = InterestCalculator.calculateInterest(loanParams);
                System.out.println(loanResult.toFormattedString(loanParams.getCurrency()));
                loanParamHistory.addLoanParams(loanParams);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error occurred while calculating result. Please try again!\n");
        }
    }

    private LoanParams captureInput() {
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

    private LoanParams captureUpdatedLoanParamInput(LoanParams oldLoanParams) {
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

    private static LoanParamHistory loanParamHistory = new LoanParamHistory();
}
