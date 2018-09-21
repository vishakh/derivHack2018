package com.regnosys.cdm.example.calculation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.regnosys.cdm.example.InterestRatePayoutCreation;
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;
import com.rosetta.model.lib.functions.IResult;
import org.isda.cdm.calculation.FixedAmount;
import org.isda.cdm.calculation.FloatingAmount;
import org.isda.cdm.functions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

/**
 * An illustration of how to implement CDM Functions and how to use them when calling CDM Calculations
 *<p>
 * CDM Functions are implemented using the <code>function</code> keyword in Rosetta and forms a contract on what the
 * function's inputs and outputs should be. The rest is left to the clients to implement.  Here we see a couple of ways
 * such functions can be implemented.
 */
public class InterestRateCalculationExample {

    private static final LocalDate REFERENCE_DATE = LocalDate.of(2018, 8, 22);

    public static void main(String[] args) throws Exception {

        // The Fixed Amount calculation as defined in CDM requires the implementation of 2 CDM Functions:
        // CalculationPeriod and DaysInPeriod
        //

        // CalculationPeriodImpl is an example implementation of extracting a 'period' from a
        // CalculationPeriodDates CDM object
        //
        CalculationPeriod calculationPeriodResolver = new CalculationPeriodImpl(REFERENCE_DATE);

        // DaysInPeriodImpl calculates the number of days in a 'period'
        //
        DaysInPeriod daysInPeriodResolver = new DaysInPeriodImpl(REFERENCE_DATE);

        // Calculate the fixed amount, using the function implementations from above
        //
        FixedAmount.CalculationResult fixedAmountResult = new FixedAmount(daysInPeriodResolver, calculationPeriodResolver)
                .calculate(InterestRatePayoutCreation.getFixedRatePayout());

        // Function implementations can be as simple as a lambda
        //
        ResolveRateIndex rateIndexResolver =
                (rateOption) -> new ResolveRateIndex.CalculationResult().setRate(new BigDecimal("0.0875"));
        GetRateSchedule rateScheduleResolver = new GetRateScheduleImpl();

        // Calculate the floating amount
        //
        FloatingAmount.CalculationResult floatingAmountResult = new FloatingAmount(rateIndexResolver,
                rateScheduleResolver, daysInPeriodResolver, calculationPeriodResolver)
                .calculate(InterestRatePayoutCreation.getFloatingRatePayout());

        // Make some assertions on the calculation results
        //
        printJson(fixedAmountResult);
        assertThat("Computed fixed amount matches expectation",
                fixedAmountResult.getFixedAmount(), closeTo(new BigDecimal("750000.0000"), new BigDecimal("0.005")));

        printJson(floatingAmountResult);
        assertThat("Computed floating amount matches expectation",
                floatingAmountResult.getFloatingAmount(), closeTo(new BigDecimal("2205479.45"), new BigDecimal("0.005")));
    }

    private static void printJson(IResult result) throws JsonProcessingException {
        String json = RosettaObjectMapper.getDefaultRosettaObjectMapper()
                .writerWithDefaultPrettyPrinter().writeValueAsString(result);
        System.out.println(json);
    }
}
