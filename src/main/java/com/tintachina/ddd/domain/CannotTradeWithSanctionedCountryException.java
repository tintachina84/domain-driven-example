package com.tintachina.ddd.domain;

public class CannotTradeWithSanctionedCountryException extends DomainException {

    public CannotTradeWithSanctionedCountryException() {
        super("Import from this country is currently prohibited!");
    }
}
