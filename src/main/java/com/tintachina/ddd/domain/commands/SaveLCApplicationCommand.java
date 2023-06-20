package com.tintachina.ddd.domain.commands;

import com.tintachina.ddd.domain.LCApplicationId;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class SaveLCApplicationCommand {
    @TargetAggregateIdentifier
    private final LCApplicationId id;

}
