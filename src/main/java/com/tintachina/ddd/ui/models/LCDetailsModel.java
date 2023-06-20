package com.tintachina.ddd.ui.models;

import com.tintachina.ddd.domain.LCApplicationId;
import lombok.Data;

@Data
public class LCDetailsModel {
    private final LCApplicationId lcApplicationId;
    private final String clientReference;
}
