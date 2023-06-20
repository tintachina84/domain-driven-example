package com.tintachina.ddd.domain.commands;

import com.tintachina.ddd.domain.Country;
import com.tintachina.ddd.domain.LCApplicationId;
import lombok.Data;

@Data
public class CreateLCApplicationCommand {

    private LCApplicationId id;
    private Country beneficiaryCountry;

    public CreateLCApplicationCommand() {
        this.id = LCApplicationId.randomId();
    }

    public CreateLCApplicationCommand(LCApplicationId id, Country beneficiaryCountry) {
        this.id = id;
        this.beneficiaryCountry = beneficiaryCountry;
    }
}
