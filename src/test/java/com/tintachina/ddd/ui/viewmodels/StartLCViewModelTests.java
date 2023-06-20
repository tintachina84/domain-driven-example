package com.tintachina.ddd.ui.viewmodels;

import com.tintachina.ddd.ui.scopes.LoggedInUserScope;
import com.tintachina.ddd.ui.services.BackendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class StartLCViewModelTests {

    @Mock
    private BackendService service;

    private StartLCViewModel viewModel;

    @BeforeEach
    void before() {
        viewModel = new StartLCViewModel(4, service);
        viewModel.setLoggedInUser(new LoggedInUserScope("admin"));
    }

    @Parameterized.Parameters(name = "{index}: For client reference \"{0}\" start disabled should be {1}")
    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of(" ", true),
                Arguments.of("      ", true),
                Arguments.of("123", true),
                Arguments.of("1234", false),
                Arguments.of("12345", false)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void shouldTest(String clientReference, boolean expected) {
        viewModel.setClientReference(clientReference);
        assertThat(viewModel.getStartDisabled()).isEqualTo(expected);
    }

    @Test
    void shouldNotEnableCreateByDefault() {
        assertThat(viewModel.getStartDisabled()).isTrue();
    }

    @Test
    void shouldNotEnableCreateIfClientReferenceLesserThanMinimumLength() {
        viewModel.setClientReference("123");
        assertThat(viewModel.getStartDisabled()).isTrue();
    }

    @Test
    void shouldEnableCreateIfClientReferenceEqualToMinimumLength() {
        viewModel.setClientReference("1234");
        assertThat(viewModel.getStartDisabled()).isFalse();
    }

    @Test
    void shouldEnableCreateIfClientReferenceGreaterThanMinimumLength() {
        viewModel.setClientReference("12345");
        assertThat(viewModel.getStartDisabled()).isFalse();
    }

    @Test
    void shouldInvokeBackendWhenStartingCreationOfNewLC() throws Exception {
        viewModel.setClientReference("12345");

        viewModel.startNewLC();

        Mockito.verify(service).startNewLC("admin", "12345");
    }

    @Test
    void shouldNotInvokeBackendIfStartButtonIsDisabled() {
        viewModel.setClientReference("");

        viewModel.startNewLC();

        Mockito.verifyNoInteractions(service);
    }
}
