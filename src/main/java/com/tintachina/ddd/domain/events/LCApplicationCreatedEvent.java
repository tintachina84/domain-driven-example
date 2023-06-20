package com.tintachina.ddd.domain.events;

import com.tintachina.ddd.domain.AdvisingBank;
import com.tintachina.ddd.domain.LCApplicationId;
import lombok.Data;

@Data
public class LCApplicationCreatedEvent {

    private final LCApplicationId id;
    private AdvisingBank advisingBank;

    public LCApplicationCreatedEvent(LCApplicationId id) {
        this.id = id;
    }

    public LCApplicationCreatedEvent(LCApplicationId id, AdvisingBank advisingBank) {
        this.id = id;
        this.advisingBank = advisingBank;
    }
}
