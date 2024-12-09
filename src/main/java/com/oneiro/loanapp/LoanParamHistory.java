package com.oneiro.loanapp;

import com.oneiro.loanapp.entity.LoanParams;

import java.util.*;

public class LoanParamHistory {
    private final Set<LoanParams> LoanParamHistory = new HashSet<>();

    public void addLoanParams(LoanParams loanParams) {
        if (loanParams != null) {
            LoanParamHistory.add(loanParams);
        }
    }

    public List<LoanParams> getLoanParamHistory() {
        return new ArrayList<>(Collections.unmodifiableSet(LoanParamHistory));
    }

    public void clearHistory() {
        LoanParamHistory.clear();
    }
}
