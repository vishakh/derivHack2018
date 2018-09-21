package com.regnosys.cdm.example.calculation.function.impl;

import com.rosetta.model.lib.records.DateImpl;
import org.isda.cdm.CalculationPeriodDates;
import org.isda.cdm.functions.CalculationPeriod;

import java.time.LocalDate;

public class NoOpCalculationPeriod implements CalculationPeriod {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public NoOpCalculationPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CalculationResult execute(CalculationPeriodDates calculationPeriodDates) {
        return new CalculationResult()
                .setStartDate(new DateImpl(startDate))
                .setEndDate(new DateImpl(endDate));
    }
}
