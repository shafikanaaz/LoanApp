package com.oneiro.loanapp.entity;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class LoanResult {

    public LoanResult(double dailyInterestWithoutMargin, double interestAmountAccrued, LocalDate accrualDate, long daysSinceStartDate, double totalInterest) {
        this.dailyInterestWithoutMargin = dailyInterestWithoutMargin;
        this.interestAmountAccrued = interestAmountAccrued;
        this.accrualDate = accrualDate;
        this.daysSinceStartDate = daysSinceStartDate;
        this.totalInterest = totalInterest;
    }

    @Override
    public String toString() {
        return "LoanResult:" +
                "\ndailyInterestWithoutMargin = " + dailyInterestWithoutMargin +
                ", \ninterestAmountAccrued = " + interestAmountAccrued +
                ", \naccrualDate = " + accrualDate +
                ", \ndaysSinceStartDate = " + daysSinceStartDate +
                ", \ntotalInterest = " + totalInterest;
    }

    public String toFormattedString(Currency currency) {
        DecimalFormat df = new DecimalFormat("0.000");
        String symbol = currency.getSymbol();
        return "LoanResult:" +
                "\nDaily Interest Without Margin = " + symbol + df.format(dailyInterestWithoutMargin) +
                ", \nInterest Amount Accrued = " + symbol + df.format(interestAmountAccrued) +
                ", \n Accrual Date = " + accrualDate +
                ", \n Days Since Start Date = " + daysSinceStartDate +
                ", \n Total Interest = " + symbol + df.format(totalInterest) +
                "\n-------------------------------------\n";
    }

    public double getDailyInterestWithoutMargin() {
        return dailyInterestWithoutMargin;
    }

    public double getInterestAmountAccrued() {
        return interestAmountAccrued;
    }

    public LocalDate getAccrualDate() {
        return accrualDate;
    }

    public long getDaysSinceStartDate() {
        return daysSinceStartDate;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    private double dailyInterestWithoutMargin;
    private double interestAmountAccrued;
    private LocalDate accrualDate;
    private long daysSinceStartDate;
    private double totalInterest;
}
