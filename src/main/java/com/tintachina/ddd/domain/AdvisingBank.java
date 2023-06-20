package com.tintachina.ddd.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdvisingBank {

    @NotNull
    private final BankId id;

    @NotNull
    private final Country country;
}
