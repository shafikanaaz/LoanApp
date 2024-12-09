package com.oneiro.loanapp.entity;

import java.time.LocalDate;
import java.util.Objects;

public class LoanParams implements Cloneable {

    public LoanParams() { }

    public LoanParams(LocalDate startDate, LocalDate endDate, double amount, Currency currency, double baseRate, double margin) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.currency = currency;
        this.baseRate = baseRate;
        this.margin = margin;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getBaseRate() {
        return baseRate;
    }

    public double getMargin() {
        return margin;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LoanParams that)) return false;
        return Double.compare(amount, that.amount) == 0
                && Double.compare(baseRate, that.baseRate) == 0
                && Double.compare(margin, that.margin) == 0
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, amount, currency, baseRate, margin);
    }

    @Override
    public String toString() {
        return "LoanParams{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", currency=" + currency +
                ", baseRate=" + baseRate +
                ", margin=" + margin +
                '}';
    }

    private LocalDate startDate;
    private LocalDate endDate;
    private double amount;
    private Currency currency;
    private double baseRate;    // In percent
    private double margin;      // In percent
}
