package com.tintachina.ddd.domain;

import com.tintachina.ddd.domain.commands.SaveLCApplicationCommand;
import com.tintachina.ddd.domain.commands.StartNewLCApplicationCommand;
import com.tintachina.ddd.domain.events.LCApplicationSavedEvent;
import com.tintachina.ddd.domain.events.LCApplicationStartedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

public class LCApplication {

    @AggregateIdentifier                                                            // <1>
    private LCApplicationId id;
    private State state;
    private AdvisingBank advisingBank;

    @SuppressWarnings("unused")
    private LCApplication() {
        // Required by the framework
    }

    @CommandHandler
    public LCApplication(StartNewLCApplicationCommand command) {
        // TODO: perform validations here
        AggregateLifecycle.apply(new LCApplicationStartedEvent(command.getId(),
                command.getApplicantId(), command.getClientReference()));
    }

    @CommandHandler
    public void save(SaveLCApplicationCommand command) {
        AggregateLifecycle.apply(new LCApplicationSavedEvent());
    }

    @EventSourcingHandler
    private void on(LCApplicationStartedEvent event) {
        this.id = event.getId();
        this.state = State.DRAFT;
    }

/*    @CommandHandler                                                                 // <2>
    public LCApplication(CreateLCApplicationCommand command) {                      // <3>
        // TODO: perform validations here
        AggregateLifecycle.apply(new LCApplicationCreatedEvent(command.getId()));   // <4>
    }

    public LCApplication(CreateLCApplicationCommand command, Set<Country> sanctioned) {
        if (sanctioned.contains(command.getBeneficiaryCountry())) {
            throw new CannotTradeWithSanctionedCountryException();
        }
        apply(new LCApplicationCreatedEvent(command.getId()));
    }

    @EventSourcingHandler                                                           // <5>
    private void on(LCApplicationCreatedEvent event) {
        this.id = event.getId();
        this.state = State.DRAFT;
        this.advisingBank = event.getAdvisingBank();
    }

    @CommandHandler
    public void submit(SubmitLCApplicationCommand command) {
        if (this.state != State.DRAFT) {                                     // <1>
            throw new AlreadySubmittedException("LC is already submitted!"); // <1>
        }
        apply(new LCApplicationSubmittedEvent(id));
    }

    @EventSourcingHandler
    private void on(LCApplicationSubmittedEvent event) {
        this.state = State.SUBMITTED;
    }

    @CommandHandler
    public void changeAdvisingBank(ChangeAdvisingBankCommand command) {
        if (!command.getAdvisingBank().equals(this.advisingBank)) {

            apply(new AdvisingBankChangedEvent(id, command.getAdvisingBank()));
        }
    }

    @EventSourcingHandler
    private void on(AdvisingBankChangedEvent event) {
        this.advisingBank = event.getAdvisingBank();
    }*/

    enum State {
        DRAFT, SUBMITTED, ISSUED
    }
}
