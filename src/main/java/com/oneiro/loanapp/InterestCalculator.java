package com.oneiro.loanapp;

import com.oneiro.loanapp.entity.LoanParams;
import com.oneiro.loanapp.entity.LoanResult;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

        return new LoanResult(dailyInterestWithoutMargin,
                interestAmountAccrued,
                accrualDate,
                daysSinceStartDate,
                totalInterest);
    }
}
