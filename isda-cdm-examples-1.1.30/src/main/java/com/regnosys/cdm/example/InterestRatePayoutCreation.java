package com.regnosys.cdm.example;

import org.isda.cdm.*;
import org.isda.cdm.CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder;
import org.isda.cdm.FloatingRateCalculation.FloatingRateCalculationBuilder;
import org.isda.cdm.NonNegativeAmountSchedule.NonNegativeAmountScheduleBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Examples of how to create ISDA CDM(TM) Java objects
 */
public class InterestRatePayoutCreation {

    public static InterestRatePayout getFloatingRatePayout() {
        return InterestRatePayout.builder()
                .setQuantityBuilder(ContractualQuantity.builder()
                        .setNotionalScheduleBuilder(NotionalSchedule.builder()
                                .setNotionalStepScheduleBuilder((NonNegativeAmountScheduleBuilder) NonNegativeAmountSchedule.builder()
                                        .setCurrency("EUR")
                                        .setInitialValue(BigDecimal.valueOf(50_000_000)))))

                .setDayCountFraction(DayCountFractionEnum.ACT_365_FIXED)

                .setCalculationPeriodDatesBuilder(CalculationPeriodDates.builder()
                        .setEffectiveDateBuilder(DateInstances.builder()
                                .setAdjustableDateBuilder(AdjustableDate.builder()
                                        .setUnadjustedDate(LocalDate.of(2018, 1, 3))
                                        .setDateAdjustmentsBuilder(BusinessDayAdjustments.builder()
                                                .setBusinessDayConvention(BusinessDayConventionEnum.NONE))))
                        .setTerminationDateBuilder(AdjustableDate.builder()
                                .setUnadjustedDate(LocalDate.of(2020, 1, 3))
                                .setDateAdjustmentsBuilder(BusinessDayAdjustments.builder()
                                        .setBusinessDayConvention(BusinessDayConventionEnum.MODFOLLOWING)
                                        .setBusinessCentersBuilder(BusinessCenters.builder()
                                                .setBusinessCentersReference("primaryBusinessCenters"))))
                        .setCalculationPeriodFrequencyBuilder((CalculationPeriodFrequencyBuilder) CalculationPeriodFrequency.builder()
                                .setRollConvention(RollConventionEnum._3)
                                .setPeriodMultiplier(6)
                                .setPeriod(PeriodExtendedEnum.M))
                        .setCalculationPeriodDatesAdjustmentsBuilder(BusinessDayAdjustments.builder()
                                .setBusinessDayConvention(BusinessDayConventionEnum.MODFOLLOWING)
                                .setBusinessCentersBuilder(BusinessCenters.builder()
                                        .setBusinessCentersReference("primaryBusinessCenters"))))

                .setInterestRateBuilder(InterestRate.builder()
                        .setFloatingRateBuilder((FloatingRateCalculationBuilder) FloatingRateCalculation.builder()
                                .setFloatingRateIndex(FloatingRateIndexEnum.EUR_LIBOR_BBA)
                                .setIndexTenorBuilder(Period.builder()
                                        .setPeriod(PeriodEnum.M)
                                        .setPeriodMultiplier(6))))

                .setPayerReceiverBuilder(PayerReceiver.builder()
                        .setPayerPartyReference("giga-bank")
                        .setReceiverPartyReference("mega-bank"))

                .build();
    }

    public static InterestRatePayout getFixedRatePayout() {
        return InterestRatePayout.builder()
                .setQuantityBuilder(ContractualQuantity.builder()
                        .setNotionalScheduleBuilder(NotionalSchedule.builder()
                                .setNotionalStepScheduleBuilder((NonNegativeAmountScheduleBuilder) NonNegativeAmountSchedule.builder()
                                        .setCurrency("EUR")
                                        .setInitialValue(BigDecimal.valueOf(50_000_000)))))

                .setDayCountFraction(DayCountFractionEnum._30E_360)

                .setCalculationPeriodDatesBuilder(CalculationPeriodDates.builder()
                        .setEffectiveDateBuilder(DateInstances.builder()
                                .setAdjustableDateBuilder(AdjustableDate.builder()
                                        .setUnadjustedDate(LocalDate.of(2018, 1, 3))
                                        .setDateAdjustmentsBuilder(BusinessDayAdjustments.builder()
                                                .setBusinessDayConvention(BusinessDayConventionEnum.NONE))))
                        .setTerminationDateBuilder(AdjustableDate.builder()
                                .setUnadjustedDate(LocalDate.of(2020, 1, 3))
                                .setDateAdjustmentsBuilder(BusinessDayAdjustments.builder()
                                        .setBusinessDayConvention(BusinessDayConventionEnum.MODFOLLOWING)
                                        .setBusinessCentersBuilder(BusinessCenters.builder()
                                                .setBusinessCentersReference("primaryBusinessCenters"))))
                        .setCalculationPeriodFrequencyBuilder((CalculationPeriodFrequencyBuilder) CalculationPeriodFrequency.builder()
                                .setRollConvention(RollConventionEnum._3)
                                .setPeriodMultiplier(3)
                                .setPeriod(PeriodExtendedEnum.M))
                        .setCalculationPeriodDatesAdjustmentsBuilder(BusinessDayAdjustments.builder()
                                .setBusinessDayConvention(BusinessDayConventionEnum.MODFOLLOWING)
                                .setBusinessCentersBuilder(BusinessCenters.builder()
                                        .setBusinessCentersReference("primaryBusinessCenters"))))

                .setInterestRateBuilder(InterestRate.builder()
                        .setFixedRateBuilder(Schedule.builder()
                                .setInitialValue(BigDecimal.valueOf(0.06))))

                .setPayerReceiverBuilder(PayerReceiver.builder()
                        .setPayerPartyReference("mega-bank")
                        .setReceiverPartyReference("giga-bank"))

                .build();
    }

    public static void main(String[] args) {
        System.out.println(getFixedRatePayout().toString());
        System.out.println(getFloatingRatePayout().toString());
    }
}
