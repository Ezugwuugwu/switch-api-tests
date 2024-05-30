package com.karrabo.interswitch_api_tests.service.transferService;

import com.karrabo.interswitch_api_tests.dtos.requests.transferService.CreditInquiryRequest;
import com.karrabo.interswitch_api_tests.dtos.requests.transferService.ListOfReceivingInstitutionsRequest;
import com.karrabo.interswitch_api_tests.dtos.responses.transferService.CreditInquiryResponse;
import com.karrabo.interswitch_api_tests.dtos.responses.transferService.ListOfReceivingInstitutionResponse;
import com.karrabo.interswitch_api_tests.exception.KarraboException;

public interface TransferService {

    CreditInquiryResponse createCreditInquiry(CreditInquiryRequest request) throws KarraboException;

    ListOfReceivingInstitutionResponse getListOfReceivingInstitution(ListOfReceivingInstitutionsRequest request) throws KarraboException;

}
