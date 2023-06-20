package com.tintachina.ddd.domain.commands;

import com.tintachina.ddd.domain.LCApplicationId;
import lombok.Data;

import java.io.Serializable;

@Data
public class StartNewLCApplicationCommand implements Serializable {
    private final String applicantId;
    private final LCApplicationId id;
    private final String clientReference;

    private StartNewLCApplicationCommand(String applicantId, String clientReference) {
        this.id = LCApplicationId.randomId();
        this.applicantId = applicantId;
        this.clientReference = clientReference;
    }

    public static StartNewLCApplicationCommand startApplication(String applicantId, String clientReference) {
        return new StartNewLCApplicationCommand(applicantId, clientReference);
    }
}
