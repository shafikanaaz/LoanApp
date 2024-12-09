package com.oneiro.loanapp.entity;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LoanResult {

    public LoanResult(double dailyInterestWithoutMargin,
                      double interestAmountAccrued,
                      LocalDate accrualDate,
                      long daysSinceStartDate,
                      double totalInterest,
                      List<Double> dailyAccruedInterest) {
        this.dailyInterestWithoutMargin = dailyInterestWithoutMargin;
        this.interestAmountAccrued = interestAmountAccrued;
        this.accrualDate = accrualDate;
        this.daysSinceStartDate = daysSinceStartDate;
        this.totalInterest = totalInterest;
        this.dailyAccruedInterest = dailyAccruedInterest;
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
        StringBuilder builder = new StringBuilder("LoanResult:");
        builder.append("\nDaily Interest Without Margin = " + symbol + df.format(dailyInterestWithoutMargin))
                .append(", \nInterest Amount Accrued = " + symbol + df.format(interestAmountAccrued))
                .append(", \n Accrual Date = " + accrualDate)
                .append(", \n Days Since Start Date = " + daysSinceStartDate)
                .append(", \n Total Interest = " + symbol + df.format(totalInterest))
                .append("\n-------------------------------------")
                .append("\nDaily Accrued Interest :\n");

        builder.append(dailyAccruedInterest.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n")));
        builder.append("\n-------------------------------------\n");

        return builder.toString();
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

    public void setDailyAccruedInterest(List<Double> dailyAccruedInterest) {
        this.dailyAccruedInterest = dailyAccruedInterest;
    }

    public List<Double> getDailyAccruedInterest() {
        return dailyAccruedInterest;
    }

    private double dailyInterestWithoutMargin;
    private double interestAmountAccrued;
    private LocalDate accrualDate;
    private long daysSinceStartDate;
    private double totalInterest;
    private List<Double> dailyAccruedInterest;
}
