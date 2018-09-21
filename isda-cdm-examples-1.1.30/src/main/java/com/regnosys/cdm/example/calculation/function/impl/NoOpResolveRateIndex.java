package com.regnosys.cdm.example.calculation.function.impl;

import org.isda.cdm.FloatingRateIndexEnum;
import org.isda.cdm.functions.ResolveRateIndex;

import java.math.BigDecimal;

public class NoOpResolveRateIndex implements ResolveRateIndex {

    private final BigDecimal rate;

    public NoOpResolveRateIndex(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public CalculationResult execute(FloatingRateIndexEnum index) {
        return new CalculationResult().setRate(rate);
    }
}
