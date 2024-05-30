package com.karrabo.interswitch_api_tests.service.implementations.transferServiceImplementation;

import com.karrabo.interswitch_api_tests.dtos.requests.transferService.CreditInquiryRequest;
import com.karrabo.interswitch_api_tests.dtos.requests.transferService.ListOfReceivingInstitutionsRequest;
import com.karrabo.interswitch_api_tests.dtos.responses.transferService.CreditInquiryResponse;
import com.karrabo.interswitch_api_tests.dtos.responses.transferService.ListOfReceivingInstitutionResponse;
import com.karrabo.interswitch_api_tests.exception.InterswitchAuthenticationException;
import com.karrabo.interswitch_api_tests.exception.KarraboException;
import com.karrabo.interswitch_api_tests.exception.OutOfPageException;
import com.karrabo.interswitch_api_tests.service.authService.AuthenticationService;
import com.karrabo.interswitch_api_tests.service.transferService.TransferService;
import com.karrabo.interswitch_api_tests.utils.ExceptionMessageConstants;
import com.karrabo.interswitch_api_tests.utils.TransferServiceDomainObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferServiceImplementation implements TransferService {

    private final AuthenticationService authenticationService;
    private final RestClient restClient;

    @Value("${interswitch.transfer.baseUrl}")
    private String TRANSFER_BASE_URL;

    @Override
    public CreditInquiryResponse createCreditInquiry(CreditInquiryRequest request) throws KarraboException {
        TransferServiceDomainObject.validateCreditInquiryRequest(request);
        ResponseEntity<CreditInquiryResponse> entity = restClient
                .post()
                .uri(TRANSFER_BASE_URL + "/inquiries/credit")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + authenticationService.getToken())
                .body(request)
                .retrieve()
                .toEntity(CreditInquiryResponse.class);
        if (entity.getStatusCode().is4xxClientError() || entity.getStatusCode().is5xxServerError()) {
            throw new InterswitchAuthenticationException(ExceptionMessageConstants.UNABLE_TO_PROCESS_REQUEST);
        }
        CreditInquiryResponse response = entity.getBody();
        assert response != null;
        return response;
    }

    @Override
    public ListOfReceivingInstitutionResponse getListOfReceivingInstitution(ListOfReceivingInstitutionsRequest institutionsRequest) throws KarraboException {
        ListOfReceivingInstitutionsRequest validatedInstitutionsRequest = TransferServiceDomainObject.validateListOfInstitutionsRequest(institutionsRequest);
        String urlCompletion = String.format("/institutions?perPage=%d&page=%d", validatedInstitutionsRequest.getPerPage(), validatedInstitutionsRequest.getPageNumber());
        ResponseEntity<ListOfReceivingInstitutionResponse> entity = restClient
                .get()
                .uri(TRANSFER_BASE_URL + urlCompletion)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + authenticationService.getToken())
                .retrieve()
                .toEntity(ListOfReceivingInstitutionResponse.class);
        return getListOfReceivingInstitutionResponse(institutionsRequest, entity);
    }

    private static ListOfReceivingInstitutionResponse getListOfReceivingInstitutionResponse(ListOfReceivingInstitutionsRequest institutionsRequest, ResponseEntity<ListOfReceivingInstitutionResponse> entity) throws KarraboException {
        if (entity.getStatusCode().is4xxClientError() || entity.getStatusCode().is5xxServerError()) {
            throw new KarraboException(ExceptionMessageConstants.UNABLE_TO_RETRIEVE_INFORMATION);
        }
        ListOfReceivingInstitutionResponse institutionResponse = entity.getBody();
        assert institutionResponse != null;
        if (
                (institutionResponse.getTotal() / institutionResponse.getPerPage()) < institutionResponse.getPage()
                || (institutionResponse.getTotal() / institutionResponse.getPerPage()) < institutionsRequest.getPageNumber()
        ) {
            throw new OutOfPageException(ExceptionMessageConstants.PAGE_EXCEEDED);
        }
        return institutionResponse;
    }

}

