package com.regnosys.cdm.example;

import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.Validator;
import org.isda.cdm.InterestRatePayout;
import org.isda.cdm.meta.InterestRatePayoutMeta;
import org.isda.cdm.validation.datarule.InterestRatePayoutActualQuantityDataRule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Illustration of how to invoke validations on a CDM object as well as individual validations
 */
public class Validation {

    public static void main(String[] args) {
        InterestRatePayout fixedRatePayout = InterestRatePayoutCreation.getFixedRatePayout();

        // All validators are referenced via a CDM object's 'fixedRatePayoutMeta' class
        InterestRatePayoutMeta fixedRatePayoutMeta = new InterestRatePayoutMeta();

        List<Validator<InterestRatePayout>> validators = new ArrayList<>();
        validators.addAll(fixedRatePayoutMeta.choiceRuleValidators());
        validators.addAll(fixedRatePayoutMeta.dataRules());

        // fixedRatePayoutMeta.validator() returns the cardinality validator for fixedRatePayout
        validators.add(fixedRatePayoutMeta.validator());

        // Run validators
        validators.stream()
                .map(validator -> validator.validate(fixedRatePayout))
                .sorted(Comparator.comparing(ValidationResult::isSuccess, Boolean::compareTo))  // failures first
                .forEach(System.out::println);

        // Individual validators can be called for further debugging
        InterestRatePayoutActualQuantityDataRule dataRule = new InterestRatePayoutActualQuantityDataRule();
        ValidationResult<InterestRatePayout> validationResult = dataRule.validate(fixedRatePayout);
        System.out.println("\nSingle validation result:\n" + validationResult);
    }

}
