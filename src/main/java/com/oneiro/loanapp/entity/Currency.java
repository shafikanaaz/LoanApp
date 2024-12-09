package com.oneiro.loanapp.entity;

public enum Currency {
    USD("$"),
    GBP("£"),
    EUR("€"),
    JPY("¥"),
    INR("₹");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name() + " (" + symbol + ")";
    }

    public static Currency fromString(String currencyCode) {
        for (Currency currency : Currency.values()) {
            if (currency.name().equalsIgnoreCase(currencyCode)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Unsupported currency: " + currencyCode +
                ". Please use one of the following: " + getSupportedCurrencies());
    }

    public static String getSupportedCurrencies() {
        StringBuilder supportedCurrencies = new StringBuilder();
        for (Currency currency : Currency.values()) {
            if (supportedCurrencies.length() > 0) {
                supportedCurrencies.append(", ");
            }
            supportedCurrencies.append(currency.name());
        }
        return supportedCurrencies.toString();
    }
}

