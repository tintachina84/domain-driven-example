package com.tintachina.ddd.ui.scopes;

import com.tintachina.ddd.domain.LCApplicationId;
import de.saxsys.mvvmfx.Scope;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LCScope implements Scope {
    private final LCApplicationId lcApplicationId;
    private final String clientReference;


}
