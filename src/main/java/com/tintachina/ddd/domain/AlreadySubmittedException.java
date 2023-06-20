package com.tintachina.ddd.domain;

public class AlreadySubmittedException extends DomainException {

    public AlreadySubmittedException(String message) {
        super(message);
    }
}
