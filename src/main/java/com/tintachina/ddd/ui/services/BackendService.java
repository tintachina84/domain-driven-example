package com.tintachina.ddd.ui.services;

import com.tintachina.ddd.domain.LCApplicationId;
import com.tintachina.ddd.domain.commands.SaveLCApplicationCommand;
import com.tintachina.ddd.ui.models.LCDetailsModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import static com.tintachina.ddd.domain.commands.StartNewLCApplicationCommand.startApplication;

@Service
public class BackendService {

    private final CommandGateway gateway;

    public BackendService(CommandGateway gateway) {
        this.gateway = gateway;
    }

    public LCApplicationId startNewLC(String applicantId, String clientReference) {
        return gateway.sendAndWait(startApplication(applicantId, clientReference));
    }

    public void saveLC(LCDetailsModel model) {
        gateway.sendAndWait(new SaveLCApplicationCommand(model.getLcApplicationId()));
    }
}
