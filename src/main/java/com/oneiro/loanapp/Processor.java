package com.oneiro.loanapp;

import com.oneiro.loanapp.entity.LoanParams;
import com.oneiro.loanapp.entity.LoanResult;

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
            LoanParams loanParams = UserInterface.captureInput();
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

                LoanParams loanParams = UserInterface.captureUpdatedLoanParamInput(records.get(option - 1));
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

    private static LoanParamHistory loanParamHistory = new LoanParamHistory();
}
