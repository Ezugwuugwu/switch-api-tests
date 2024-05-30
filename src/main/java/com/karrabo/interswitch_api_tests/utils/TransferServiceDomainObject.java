package com.karrabo.interswitch_api_tests.utils;

import com.karrabo.interswitch_api_tests.dtos.requests.transferService.CreditInquiryRequest;
import com.karrabo.interswitch_api_tests.dtos.requests.transferService.ListOfReceivingInstitutionsRequest;
import com.karrabo.interswitch_api_tests.exception.InvalidArgumentProvidedException;
import org.apache.commons.lang3.StringUtils;

public class TransferServiceDomainObject {

    public static ListOfReceivingInstitutionsRequest validateListOfInstitutionsRequest(ListOfReceivingInstitutionsRequest institutionsRequest) {
        if (institutionsRequest.getPerPage() == 0) {
            institutionsRequest.setPerPage(50L);
        }
        return institutionsRequest;
    }

    public static void validateCreditInquiryRequest(CreditInquiryRequest request) throws InvalidArgumentProvidedException {
        if (
                StringUtils.isEmpty(request.getSourceAccountNumber())
                || StringUtils.isEmpty(request.getSourceAccountName())
                || StringUtils.isEmpty(request.getDestinationAccountNumber())
                || StringUtils.isEmpty(request.getDestinationInstitutionCode())
                || StringUtils.isEmpty(request.getCurrencyCode())
                || StringUtils.isEmpty(request.getClientRef())
                || request.getTransactionAmount() < 10L
        ) {
            throw new InvalidArgumentProvidedException(ExceptionMessageConstants.INADEQUATE_DETAILS_PROVIDED);
        }
    }
}
