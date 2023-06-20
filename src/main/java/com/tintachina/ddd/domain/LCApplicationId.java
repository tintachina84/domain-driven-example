package com.tintachina.ddd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LCApplicationId {

    private UUID id;

    public static LCApplicationId randomId() {
        return new LCApplicationId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
