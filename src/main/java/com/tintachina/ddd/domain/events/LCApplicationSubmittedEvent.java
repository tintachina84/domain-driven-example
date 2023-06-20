package com.tintachina.ddd.domain.events;

import com.tintachina.ddd.domain.LCApplicationId;

public class LCApplicationSubmittedEvent {

    private final LCApplicationId applicationId;

    public LCApplicationSubmittedEvent(LCApplicationId applicationId) {
        this.applicationId = applicationId;
    }
}
