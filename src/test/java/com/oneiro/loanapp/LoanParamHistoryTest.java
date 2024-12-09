package com.oneiro.loanapp;

import com.oneiro.loanapp.entity.Currency;
import com.oneiro.loanapp.entity.LoanParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanParamHistoryTest {

    private LoanParamHistory loanParamHistory;
    private LoanParams loanParams1;
    private LoanParams loanParams2;
    private LoanParams loanParams3;

    @BeforeEach
    void setUp() {
        loanParamHistory = new LoanParamHistory();

        loanParams1 = new LoanParams(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1),
                10000.0,
                Currency.USD,
                5.0,
                2.0
        );

        loanParams2 = new LoanParams(
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                20000.0,
                Currency.GBP,
                4.0,
                3.0
        );

        loanParams3 = new LoanParams(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1),
                10000.0,
                Currency.USD,
                5.0,
                2.0
        ); // Same as loanParams1 to test duplicates
    }

    @Test
    void addLoanParams_happyPath() {
        loanParamHistory.addLoanParams(loanParams1);
        List<LoanParams> history = loanParamHistory.getLoanParamHistory();

        assertNotNull(history);
        assertEquals(1, history.size());
        assertTrue(history.contains(loanParams1));
    }

    @Test
    void addLoanParams_addDuplicateLoanParams() {
        loanParamHistory.addLoanParams(loanParams1);
        loanParamHistory.addLoanParams(loanParams3); // Duplicate entry

        List<LoanParams> history = loanParamHistory.getLoanParamHistory();

        assertNotNull(history);
        assertEquals(1, history.size());
    }

    @Test
    void addLoanParams_addNullLoanParams() {
        loanParamHistory.addLoanParams(null);

        List<LoanParams> history = loanParamHistory.getLoanParamHistory();

        assertNotNull(history);
        assertEquals(0, history.size());
    }

    @Test
    void getLoanParamHistory_happyPath() {
        loanParamHistory.addLoanParams(loanParams1);
        loanParamHistory.addLoanParams(loanParams2);

        List<LoanParams> history = loanParamHistory.getLoanParamHistory();

        assertNotNull(history);
        assertEquals(2, history.size());
        assertTrue(history.contains(loanParams1));
        assertTrue(history.contains(loanParams2));
    }

    @Test
    void getLoanParamHistory_noRecords() {
        List<LoanParams> history = loanParamHistory.getLoanParamHistory();

        assertNotNull(history);
        assertEquals(0, history.size());
    }

    @Test
    void clearHistory_successful() {
        loanParamHistory.addLoanParams(loanParams1);
        loanParamHistory.addLoanParams(loanParams2);

        loanParamHistory.clearHistory();

        List<LoanParams> history = loanParamHistory.getLoanParamHistory();

        assertNotNull(history);
        assertEquals(0, history.size());
    }

    @Test
    void addLoanParams_multipleAdditions() {
        loanParamHistory.addLoanParams(loanParams1);
        loanParamHistory.addLoanParams(loanParams2);
        loanParamHistory.addLoanParams(loanParams1); // Adding loanParams1 again

        List<LoanParams> history = loanParamHistory.getLoanParamHistory();

        assertEquals(2, history.size());
        assertTrue(history.contains(loanParams1));
        assertTrue(history.contains(loanParams2));
    }
}
