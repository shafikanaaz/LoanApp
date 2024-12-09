package com.oneiro.loanapp;

import com.oneiro.loanapp.entity.LoanParams;
import com.oneiro.loanapp.entity.LoanResult;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class InterestCalculator {

    public static LoanResult calculateInterest(LoanParams loanParams) {
        System.out.println("\nCalculating interest...");

        double fractionalBaseRate = loanParams.getBaseRate() / 100.0;
        double totalRate = (loanParams.getBaseRate() + loanParams.getMargin()) / 100.0;

        // Get today's date as accrual date
        LocalDate accrualDate = LocalDate.now().isAfter(loanParams.getEndDate())
                ? loanParams.getEndDate() : LocalDate.now();
        long daysSinceStartDate = ChronoUnit.DAYS.between(loanParams.getStartDate(), accrualDate);
        long totalDays = ChronoUnit.DAYS.between(loanParams.getStartDate(), loanParams.getEndDate());

        double totalInterest = loanParams.getAmount() * totalRate * ((double) totalDays / 365.0);
        double totalInterestWithoutMargin = loanParams.getAmount() * fractionalBaseRate * ((double) totalDays / 365.0);
        double dailyInterestWithoutMargin = totalInterestWithoutMargin / totalDays;

        double interestAmountAccrued = daysSinceStartDate > 0 ?
                totalInterest * ((double) daysSinceStartDate / totalDays) : 0.0;

        // Daily accrued interest for each day of the loan period
        List<Double> dailyAccruedInterest = new ArrayList<>((int) totalDays);
        double dailyInterest = totalInterest / totalDays;
        double accruedInterest = 0.0;
        for (int i = 0; i < totalDays; ++i) {
            accruedInterest += dailyInterest;
            dailyAccruedInterest.add(accruedInterest);
        }


        return new LoanResult(dailyInterestWithoutMargin,
                interestAmountAccrued,
                accrualDate,
                daysSinceStartDate,
                totalInterest,
                dailyAccruedInterest);
    }
}
