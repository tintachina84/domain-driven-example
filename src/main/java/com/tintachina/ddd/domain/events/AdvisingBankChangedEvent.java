package com.tintachina.ddd.domain.events;

import com.tintachina.ddd.domain.AdvisingBank;
import com.tintachina.ddd.domain.LCApplicationId;
import lombok.Data;

@Data
public class AdvisingBankChangedEvent {

    private final LCApplicationId applicationId;
    private final AdvisingBank advisingBank;
}
