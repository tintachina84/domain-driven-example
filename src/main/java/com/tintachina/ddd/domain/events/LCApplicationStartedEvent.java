package com.tintachina.ddd.domain.events;

import com.tintachina.ddd.domain.AdvisingBank;
import com.tintachina.ddd.domain.LCApplicationId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LCApplicationStartedEvent {

    private LCApplicationId id;
    private String applicantId;
    private String clientReference;
    private AdvisingBank advisingBank;

    public LCApplicationStartedEvent(LCApplicationId id, String applicantId, String clientReference) {
        this.id = id;
        this.applicantId = applicantId;
        this.clientReference = clientReference;
    }
}
