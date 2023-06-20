package com.tintachina.ddd.domain.commands;

import com.tintachina.ddd.domain.AdvisingBank;
import com.tintachina.ddd.domain.LCApplicationId;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ChangeAdvisingBankCommand {

    @TargetAggregateIdentifier
    private final LCApplicationId applicationId;
    private final AdvisingBank advisingBank;
}
