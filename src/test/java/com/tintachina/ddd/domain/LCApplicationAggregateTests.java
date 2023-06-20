package com.tintachina.ddd.domain;

import com.tintachina.ddd.domain.commands.ChangeAdvisingBankCommand;
import com.tintachina.ddd.domain.commands.CreateLCApplicationCommand;
import com.tintachina.ddd.domain.commands.SaveLCApplicationCommand;
import com.tintachina.ddd.domain.commands.SubmitLCApplicationCommand;
import com.tintachina.ddd.domain.events.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tintachina.ddd.domain.commands.StartNewLCApplicationCommand.startApplication;
import static org.axonframework.test.matchers.Matchers.*;
import static org.hamcrest.Matchers.any;

public class LCApplicationAggregateTests {

    private FixtureConfiguration<LCApplication> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(LCApplication.class);
    }

    @Test
    void shouldPublishLCApplicationIsStarted() {
        fixture.given()

                .when(startApplication("admin", "My Test"))

                .expectEventsMatching(exactSequenceOf(
                        messageWithPayload(any(LCApplicationStartedEvent.class)),
                        andNoMore()
                ));
    }

    @Test
    void shouldSaveLCApplication() {
        final LCApplicationId id = LCApplicationId.randomId();
        final LCApplicationStartedEvent started = new LCApplicationStartedEvent(id,
                "test-user", "Test LC");
        fixture.given(started)
                .when(new SaveLCApplicationCommand(id))
                .expectEvents(new LCApplicationSavedEvent());
    }


/*
    private FixtureConfiguration<LCApplication> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(LCApplication.class);
    }

    @Test
    void shouldPublishLCApplicationCreated() {
        fixture.given()

                .when(new CreateLCApplicationCommand())

                .expectEventsMatching(exactSequenceOf(
                        messageWithPayload(any(LCApplicationCreatedEvent.class)),
                        andNoMore()
                ));
    }

    @Test
    void shouldNotAllowSubmitOnAnAlreadySubmittedLC() {
        final LCApplicationId applicationId = LCApplicationId.randomId();

        fixture.given(
                        new LCApplicationCreatedEvent(applicationId),
                        new LCApplicationSubmittedEvent(applicationId))

                .when(new SubmitLCApplicationCommand(applicationId))

                .expectException(AlreadySubmittedException.class)
                .expectNoEvents();
    }

    @Test
    void shouldAllowSubmitOnlyInDraftState() {
        final LCApplicationId applicationId = LCApplicationId.randomId();

        fixture.given(new LCApplicationCreatedEvent(applicationId))
                .when(new SubmitLCApplicationCommand(applicationId))
                .expectEvents(new LCApplicationSubmittedEvent(applicationId));
    }

    @Test
    void shouldAllowChangingAdvisingBank() {
        final LCApplicationId applicationId = LCApplicationId.randomId();

        final AdvisingBank oldAdvisingBank = AdvisingBank.builder()
                .id(BankId.randomId())
                .country(Country.SOKOVIA).build();
        final AdvisingBank newAdvisingBank = AdvisingBank.builder()
                .id(BankId.randomId())
                .country(Country.WAKANDA).build();

        fixture.given(new LCApplicationCreatedEvent(applicationId, oldAdvisingBank))
                .when(new ChangeAdvisingBankCommand(applicationId, newAdvisingBank))
                .expectEvents(new AdvisingBankChangedEvent(applicationId, newAdvisingBank));
    }*/
}
