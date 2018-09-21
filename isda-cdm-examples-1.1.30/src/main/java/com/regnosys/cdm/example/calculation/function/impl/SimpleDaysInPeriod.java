package com.regnosys.cdm.example.calculation.function.impl;

import org.isda.cdm.CalculationPeriodDates;
import org.isda.cdm.functions.DaysInPeriod;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SimpleDaysInPeriod implements DaysInPeriod {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public SimpleDaysInPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CalculationResult execute(CalculationPeriodDates calculationPeriodDates) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        return new CalculationResult().setDays((int) days);
    }
}
