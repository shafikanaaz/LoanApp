package com.oneiro.loanapp;

import com.oneiro.loanapp.entity.Currency;
import com.oneiro.loanapp.entity.LoanParams;
import com.oneiro.loanapp.entity.LoanResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class InterestCalculatorTest {

    private LoanParams loanParams;
    private LocalDate startDate;
    private LocalDate endDate;

    @BeforeEach
    void setUp() {
        startDate = LocalDate.of(2023, 1, 1);
        endDate = LocalDate.of(2024, 1, 1);
    }

    @Test
    void calculateInterest_happyPath() {
        loanParams = new LoanParams(startDate, endDate, 10000.0, Currency.USD, 5.0, 2.0);

        LoanResult result = InterestCalculator.calculateInterest(loanParams);

        assertNotNull(result);
        assertEquals(10000.0 * 0.07 * (365.0 / 365.0), result.getTotalInterest());
        assertEquals(10000.0 * 0.05 * (1.0 / 365.0), result.getDailyInterestWithoutMargin());
        assertTrue(result.getInterestAmountAccrued() >= 0);
    }

    @Test
    void calculateInterest_withZeroDaysAccrued() {
        // Scenario where start date is today
        loanParams = new LoanParams(LocalDate.now(), LocalDate.now().plusDays(100), 10000.0, Currency.USD, 5.0, 2.0);

        LoanResult result = InterestCalculator.calculateInterest(loanParams);

        assertNotNull(result);
        assertEquals(0.0, result.getInterestAmountAccrued());
    }


    @Test
    void calculateInterest_withDifferentRates() {
        loanParams = new LoanParams(startDate, endDate, 10000.0, Currency.USD, 6.0, 1.5);

        LoanResult result = InterestCalculator.calculateInterest(loanParams);

        assertNotNull(result);
        assertEquals(10000.0 * (6.0 + 1.5) / 100.0 * (365.0 / 365.0), result.getTotalInterest());
    }

    @Test
    void calculateInterest_withZeroMargin() {
        loanParams = new LoanParams(startDate, endDate, 10000.0, Currency.USD, 6.0, 0.0);

        LoanResult result = InterestCalculator.calculateInterest(loanParams);

        assertNotNull(result);
        assertEquals(10000.0 * 6.0  / 100.0 * (365.0 / 365.0), result.getTotalInterest());
    }

    @Test
    void calculateInterest_withZeroBaseRateAndMargin() {
        loanParams = new LoanParams(startDate, endDate, 10000.0, Currency.USD, 0.0, 0.0);

        LoanResult result = InterestCalculator.calculateInterest(loanParams);

        assertNotNull(result);
        assertEquals(0.0, result.getTotalInterest());
    }

    @Test
    void calculateInterest_withNegativeAmount() {
        loanParams = new LoanParams(startDate, endDate, -10000.0, Currency.USD, 5.0, 2.0);

        LoanResult result = InterestCalculator.calculateInterest(loanParams);

        assertNotNull(result);
        assertTrue(result.getTotalInterest() < 0);
    }

    @Test
    void calculateInterest_withSameStartAndEndDate() {
        loanParams = new LoanParams(LocalDate.now(), LocalDate.now(), 10000.0, Currency.USD, 5.0, 2.0);

        LoanResult result = InterestCalculator.calculateInterest(loanParams);

        assertNotNull(result);
        assertEquals(0.0, result.getTotalInterest());
    }

    @Test
    void calculateInterest_withPastAccrualDate() {
        LocalDate pastAccrualDate = LocalDate.of(2022, 1, 1);
        loanParams = new LoanParams(pastAccrualDate, endDate, 10000.0, Currency.USD, 5.0, 2.0);

        LoanResult result = InterestCalculator.calculateInterest(loanParams);

        assertNotNull(result);
        assertTrue(result.getInterestAmountAccrued() > 0);
    }
}
