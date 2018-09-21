package com.regnosys.cdm.example;

import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.qualify.QualifyResultsExtractor;
import org.isda.cdm.EconomicTerms;
import org.isda.cdm.meta.EconomicTermsMeta;

public class Qualification {

    private static String economicTermsJson = "{\n" +
            "      \"payout\" : {\n" +
            "        \"interestRatePayout\" : [ {\n" +
            "          \"calculationPeriodDates\" : {\n" +
            "            \"calculationPeriodDatesAdjustments\" : {\n" +
            "              \"businessCenters\" : {\n" +
            "                \"businessCenter\" : [ \"EUTA\" ]\n" +
            "              },\n" +
            "              \"businessDayConvention\" : \"MODFOLLOWING\"\n" +
            "            },\n" +
            "            \"calculationPeriodFrequency\" : {\n" +
            "              \"period\" : \"Y\",\n" +
            "              \"periodMultiplier\" : 1,\n" +
            "              \"rollConvention\" : \"_6\"\n" +
            "            },\n" +
            "            \"effectiveDate\" : {\n" +
            "              \"adjustableDate\" : {\n" +
            "                \"dateAdjustments\" : {\n" +
            "                  \"businessDayConvention\" : \"NONE\"\n" +
            "                },\n" +
            "                \"unadjustedDate\" : \"2015-03-06\"\n" +
            "              }\n" +
            "            },\n" +
            "            \"id\" : \"fixedCalcPeriodDates1\",\n" +
            "            \"terminationDate\" : {\n" +
            "              \"dateAdjustments\" : {\n" +
            "                \"businessCenters\" : {\n" +
            "                  \"businessCenter\" : [ \"EUTA\" ]\n" +
            "                },\n" +
            "                \"businessDayConvention\" : \"MODFOLLOWING\"\n" +
            "              },\n" +
            "              \"unadjustedDate\" : \"2025-03-06\"\n" +
            "            }\n" +
            "          },\n" +
            "          \"dayCountFraction\" : \"_30_360\",\n" +
            "          \"id\" : \"fixedLeg1\",\n" +
            "          \"interestRate\" : {\n" +
            "            \"fixedRate\" : {\n" +
            "              \"initialValue\" : 0.006982\n" +
            "            }\n" +
            "          },\n" +
            "          \"payerReceiver\" : {\n" +
            "            \"payerPartyReference\" : \"party1\",\n" +
            "            \"receiverPartyReference\" : \"party2\"\n" +
            "          },\n" +
            "          \"paymentDates\" : {\n" +
            "            \"calculationPeriodDatesReference\" : \"fixedCalcPeriodDates1\",\n" +
            "            \"id\" : \"paymentDates1\",\n" +
            "            \"payRelativeTo\" : \"CALCULATION_PERIOD_END_DATE\",\n" +
            "            \"paymentDatesAdjustments\" : {\n" +
            "              \"businessCenters\" : {\n" +
            "                \"businessCenter\" : [ \"EUTA\" ]\n" +
            "              },\n" +
            "              \"businessDayConvention\" : \"MODFOLLOWING\"\n" +
            "            },\n" +
            "            \"paymentFrequency\" : {\n" +
            "              \"period\" : \"Y\",\n" +
            "              \"periodMultiplier\" : 1\n" +
            "            }\n" +
            "          },\n" +
            "          \"quantity\" : {\n" +
            "            \"notionalSchedule\" : {\n" +
            "              \"notionalStepSchedule\" : {\n" +
            "                \"initialValue\" : 10000000,\n" +
            "                \"currency\" : \"EUR\"\n" +
            "              }\n" +
            "            }\n" +
            "          }\n" +
            "        }, {\n" +
            "          \"calculationPeriodDates\" : {\n" +
            "            \"calculationPeriodDatesAdjustments\" : {\n" +
            "              \"businessCenters\" : {\n" +
            "                \"businessCenter\" : [ \"EUTA\" ]\n" +
            "              },\n" +
            "              \"businessDayConvention\" : \"MODFOLLOWING\"\n" +
            "            },\n" +
            "            \"calculationPeriodFrequency\" : {\n" +
            "              \"period\" : \"M\",\n" +
            "              \"periodMultiplier\" : 6,\n" +
            "              \"rollConvention\" : \"_6\"\n" +
            "            },\n" +
            "            \"effectiveDate\" : {\n" +
            "              \"adjustableDate\" : {\n" +
            "                \"dateAdjustments\" : {\n" +
            "                  \"businessDayConvention\" : \"NONE\"\n" +
            "                },\n" +
            "                \"unadjustedDate\" : \"2015-03-06\"\n" +
            "              }\n" +
            "            },\n" +
            "            \"id\" : \"floatingCalcPeriodDates2\",\n" +
            "            \"terminationDate\" : {\n" +
            "              \"dateAdjustments\" : {\n" +
            "                \"businessCenters\" : {\n" +
            "                  \"businessCenter\" : [ \"EUTA\" ]\n" +
            "                },\n" +
            "                \"businessDayConvention\" : \"MODFOLLOWING\"\n" +
            "              },\n" +
            "              \"unadjustedDate\" : \"2025-03-06\"\n" +
            "            }\n" +
            "          },\n" +
            "          \"dayCountFraction\" : \"ACT_360\",\n" +
            "          \"id\" : \"floatingLeg2\",\n" +
            "          \"interestRate\" : {\n" +
            "            \"floatingRate\" : {\n" +
            "              \"floatingRateIndex\" : \"EUR_EURIBOR_REUTERS\",\n" +
            "              \"indexTenor\" : {\n" +
            "                \"period\" : \"M\",\n" +
            "                \"periodMultiplier\" : 6\n" +
            "              }\n" +
            "            }\n" +
            "          },\n" +
            "          \"payerReceiver\" : {\n" +
            "            \"payerPartyReference\" : \"party2\",\n" +
            "            \"receiverPartyReference\" : \"party1\"\n" +
            "          },\n" +
            "          \"paymentDates\" : {\n" +
            "            \"calculationPeriodDatesReference\" : \"floatingCalcPeriodDates2\",\n" +
            "            \"id\" : \"paymentDates2\",\n" +
            "            \"payRelativeTo\" : \"CALCULATION_PERIOD_END_DATE\",\n" +
            "            \"paymentDatesAdjustments\" : {\n" +
            "              \"businessCenters\" : {\n" +
            "                \"businessCenter\" : [ \"EUTA\" ]\n" +
            "              },\n" +
            "              \"businessDayConvention\" : \"MODFOLLOWING\"\n" +
            "            },\n" +
            "            \"paymentFrequency\" : {\n" +
            "              \"period\" : \"M\",\n" +
            "              \"periodMultiplier\" : 6\n" +
            "            }\n" +
            "          },\n" +
            "          \"quantity\" : {\n" +
            "            \"notionalSchedule\" : {\n" +
            "              \"notionalStepSchedule\" : {\n" +
            "                \"initialValue\" : 10000000,\n" +
            "                \"currency\" : \"EUR\"\n" +
            "              }\n" +
            "            }\n" +
            "          },\n" +
            "          \"resetDates\" : {\n" +
            "            \"calculationPeriodDatesReference\" : \"floatingCalcPeriodDates2\",\n" +
            "            \"fixingDates\" : {\n" +
            "              \"period\" : \"D\",\n" +
            "              \"periodMultiplier\" : -2,\n" +
            "              \"dayType\" : \"BUSINESS\",\n" +
            "              \"businessCenters\" : {\n" +
            "                \"businessCenter\" : [ \"EUTA\" ]\n" +
            "              },\n" +
            "              \"businessDayConvention\" : \"NONE\",\n" +
            "              \"dateRelativeTo\" : \"resetDates2\"\n" +
            "            },\n" +
            "            \"id\" : \"resetDates2\",\n" +
            "            \"resetDatesAdjustments\" : {\n" +
            "              \"businessCenters\" : {\n" +
            "                \"businessCenter\" : [ \"EUTA\" ]\n" +
            "              },\n" +
            "              \"businessDayConvention\" : \"MODFOLLOWING\"\n" +
            "            },\n" +
            "            \"resetFrequency\" : {\n" +
            "              \"period\" : \"M\",\n" +
            "              \"periodMultiplier\" : 6\n" +
            "            },\n" +
            "            \"resetRelativeTo\" : \"CALCULATION_PERIOD_START_DATE\"\n" +
            "          }\n" +
            "        } ]\n" +
            "      },\n" +
            "      \"rosettaKeyValue\" : \"4bb465a4\"\n" +
            "    }";

    public static void main(String[] args) throws Exception {
        EconomicTerms economicTerms = RosettaObjectMapper.getDefaultRosettaObjectMapper()
                .readValue(economicTermsJson, EconomicTerms.class);

        QualifyResultsExtractor<EconomicTerms> extractor = new QualifyResultsExtractor<>(
                new EconomicTermsMeta().getQualifyFunctions(), economicTerms);

        String result = extractor.getOnlySuccessResult().map(QualifyResult::getName).orElse("Failed to qualify");

        System.out.println(result);
    }
}
