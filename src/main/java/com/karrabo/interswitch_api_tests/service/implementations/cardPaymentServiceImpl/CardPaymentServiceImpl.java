package com.karrabo.interswitch_api_tests.service.implementations.cardPaymentServiceImpl;

import com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests.*;
import com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses.*;
import com.karrabo.interswitch_api_tests.exception.*;
import com.karrabo.interswitch_api_tests.models.UssdBank;
import com.karrabo.interswitch_api_tests.service.authService.AuthenticationService;
import com.karrabo.interswitch_api_tests.service.cardPaymentService.CardPaymentService;
import com.karrabo.interswitch_api_tests.utils.ExceptionMessageConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class CardPaymentServiceImpl implements CardPaymentService {

    private final RestTemplate restTemplate;
    private final AuthenticationService authenticationService;


    @Value("${interswitch.validate.recurrent.url}")
    private String validateRecurrentUrl;

    @Value("${interswitch.validate.purchase.recurrent.url}")
    private String purchaseRecurrentUrl;

    @Value("${interswitch.auth.token}")
    private String authToken;

    @Value("${interswitch.confirm.dynamic.transfer.url}")
    private String confirmDynamicTransferUrl;

    @Value("${interswitch.get.transactions.url}")
    private String getTransactionsUrl;

    @Value("${interswitch.get.refund.transactions.url}")
    private String getRefundsUrl;

    @Value("${interswitch.get.refund.info.url}")
    private String getRefundsInfoUrl;

    @Value("${interswitch.create.refund.transaction.url}")
    private String createRefundTransactionUrl;

    @Value("${interswitch.pay.with.USSD.url}")
    private String UssdPaymentUrl;

    @Value("${interswitch.generate.Qr.url}")
    private String generateQrPaymentUrl;

    @Value("${interswitch.pay.with.virtual.account.url}")
    private String payWithVirtualAccountUrl;

    @Value("${interswitch.get.wallet.cards.url}")
    private String getWalletCardsUrl;

    @Value("${interswitch.generate.alternative.payment.url}")
    private String alternativePaymentOptionUrl;

    @Value("${interswitch.USSD_banks.url}")
    private String ussdBanksUrl;


    public CardPaymentServiceImpl(RestTemplate restTemplate, AuthenticationService authenticationService) {
        this.restTemplate = restTemplate;
        this.authenticationService = authenticationService;
    }


    @Override
    public String authenticateOtp(AuthenticateOtpRequest authenticateOtpRequest) {
        String accessToken = authenticationService.authenticate();
        String url = "https://qa.interswitchng.com/api/v3/purchases/otps/auths";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"paymentId\": \"" + authenticateOtpRequest.getPaymentId() + "\", \"otp\": \"" + authenticateOtpRequest.getOtp() + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to authenticate OTP: " + response.getBody());
        }
    }


    @Override
    public TokenizeCardResponse TokenizeCard_Recurrent(TokenizeCardRequest tokenizeCardRequest) {
        final String VALIDATE_RECURRENT_URL = validateRecurrentUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<TokenizeCardRequest> entity = new HttpEntity<>(tokenizeCardRequest, headers);
        ResponseEntity<TokenizeCardResponse> response = restTemplate.exchange(
                VALIDATE_RECURRENT_URL,
                HttpMethod.POST,
                entity,
                TokenizeCardResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to validate recurrent transaction: " + response.getBody());
        }
    }



    @Override
    public PurchaseRecurrentResponse purchase_Recurrent(PurchaseRecurrentRequest purchaseRecurrentRequest) throws RecurrentPurchaseException {

        final String VALIDATE_PURCHASE_RECURRENT_URL = purchaseRecurrentUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<PurchaseRecurrentRequest> entity = new HttpEntity<>(purchaseRecurrentRequest, headers);
        ResponseEntity<PurchaseRecurrentResponse> response = restTemplate.exchange(
                VALIDATE_PURCHASE_RECURRENT_URL,
                HttpMethod.POST,
                entity,
                PurchaseRecurrentResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RecurrentPurchaseException(ExceptionMessageConstants.ERROR_VALIDATING_RECURRENT_PURCHASE + response.getBody());
        }
    }



    @Override
    public ConfirmDynamicTransferResponse confirmDynamicTransfer(String merchantCode, String transactionReference) throws TransactionException, ConfirmDynamicTransactionException {
        final String CONFIRM_DYNAMIC_TRANSFER_URL = confirmDynamicTransferUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CONFIRM_DYNAMIC_TRANSFER_URL)
                .queryParam("merchantCode", merchantCode)
                .queryParam("transactionReference", transactionReference);

        ResponseEntity<ConfirmDynamicTransferResponse> response;
        try {
            response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    ConfirmDynamicTransferResponse.class
            );
        } catch (Exception message) {
            throw new ConfirmDynamicTransactionException(ExceptionMessageConstants.ERROR_CONFIRMING_DYNAMIC_TRANSFER, message);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new TransactionException(ExceptionMessageConstants.ERROR_CONFIRMING_DYNAMIC_TRANSFER + response.getBody());
        }
    }



    @Override
    public GetTransactionsResponse getTransactions(GetTransactionRequests getTransactionRequests) throws TransactionException, TransactionNotFoundException {
        final String GET_TRANSACTIONS_URL = getTransactionsUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_TRANSACTIONS_URL)
                .queryParam("merchantCode", getTransactionRequests.getMerchantCode())
                .queryParam("transactionReference", getTransactionRequests.getTransactionReference())
                .queryParam("startDate", getTransactionRequests.getStartDate())
                .queryParam("endDate", getTransactionRequests.getEndDate())
                .queryParam("pageSize", getTransactionRequests.getPageSize())
                .queryParam("pageNum", getTransactionRequests.getPageNum());

        if (getTransactionRequests.getTransactionReference() != null && !getTransactionRequests.getTransactionReference().isEmpty()) {
            builder.queryParam("transactionReference", getTransactionRequests.getTransactionReference());
        }

        ResponseEntity<GetTransactionsResponse> response;
        try {
            response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    GetTransactionsResponse.class
            );
        } catch (Exception message) {
            throw new TransactionNotFoundException(ExceptionMessageConstants.ERROR_FETCHING_TRANSACTIONS, message);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new TransactionException(ExceptionMessageConstants.ERROR_FETCHING_TRANSACTIONS + response.getBody());
        }
    }



    @Override
    public GetRefundsResponse getRefunds(GetRefundsTransactionsRequests getRefundsTransactions) throws TransactionException, GetRefundTransactionException {

        final String GET_REFUNDS_URL = getRefundsUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_REFUNDS_URL)
                .queryParam("merchantCode", getRefundsTransactions.getMerchantCode())
                .queryParam("startDate", getRefundsTransactions.getStartDate())
                .queryParam("endDate", getRefundsTransactions.getEndDate())
                .queryParam("pageSize", getRefundsTransactions.getPageSize())
                .queryParam("pageNum", getRefundsTransactions.getPageNum());

        ResponseEntity<GetRefundsResponse> response;
        try {
            response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    GetRefundsResponse.class
            );
        } catch (Exception message) {
            throw new GetRefundTransactionException(ExceptionMessageConstants.ERROR_GETTING_REFUND_TRANSACTIONS, message);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new TransactionException(ExceptionMessageConstants.ERROR_GETTING_REFUND_TRANSACTIONS + response.getBody());
        }
    }



    @Override
    public GetRefundInfoResponse getRefundInfo(String refundReference) throws TransactionException, RefundInfoNotFoundException {
        final String GET_REFUND_INFO_URL = getRefundsInfoUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_REFUND_INFO_URL)
                .queryParam("refundReference", refundReference);

        ResponseEntity<GetRefundInfoResponse> response;
        try {
            response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    GetRefundInfoResponse.class
            );
        } catch (Exception message) {
            throw new RefundInfoNotFoundException(ExceptionMessageConstants.ERROR_FETCHING_REFUND_INFO, message);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new TransactionException(ExceptionMessageConstants.ERROR_FETCHING_REFUND_INFO + response.getBody());
        }
    }



    @Override
    public CreateRefundTransactionResponse createRefundTransaction(CreateRefundTransactionRequest refundCreateRequest) throws TransactionException, CreateTransactionException {
        final String CREATE_REFUND_URL = createRefundTransactionUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<CreateRefundTransactionRequest> entity = new HttpEntity<>(refundCreateRequest, headers);

        ResponseEntity<CreateRefundTransactionResponse> response;
        try {
            response = restTemplate.postForEntity(
                    CREATE_REFUND_URL,
                    entity,
                    CreateRefundTransactionResponse.class
            );
        } catch (Exception message) {
            throw new CreateTransactionException(ExceptionMessageConstants.ERROR_CREATING_REFUND_TRANSACTION, message);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new TransactionException(ExceptionMessageConstants.ERROR_CREATING_REFUND_TRANSACTION + response.getBody());
        }
    }

    @Override
    public UssdPaymentResponse generateUssdPayment(UssdPaymentRequest ussdPaymentRequest) throws TransactionException, USSDPaymentException {
        final String PAY_WITH_USSD_URL = UssdPaymentUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<UssdPaymentRequest> entity = new HttpEntity<>(ussdPaymentRequest, headers);

        ResponseEntity<UssdPaymentResponse> response;
        try {
            response = restTemplate.postForEntity(
                    PAY_WITH_USSD_URL,
                    entity,
                    UssdPaymentResponse.class
            );
        } catch (Exception message) {
            throw new USSDPaymentException(ExceptionMessageConstants.ERROR_PAYING_WITH_USSD, message);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new TransactionException(ExceptionMessageConstants.ERROR_PAYING_WITH_USSD + response.getBody());
        }
    }

    @Override
    public QrPaymentResponse generateQrPayment(QrPaymentRequest qrPaymentRequest) throws TransactionException, QrPaymentException {
        final String GENERATE_QR_PAYMENT_URL = generateQrPaymentUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<QrPaymentRequest> entity = new HttpEntity<>(qrPaymentRequest, headers);

        ResponseEntity<QrPaymentResponse> response;
        try {
            response = restTemplate.postForEntity(
                    GENERATE_QR_PAYMENT_URL,
                    entity,
                    QrPaymentResponse.class
            );
        } catch (Exception message) {
            throw new QrPaymentException(ExceptionMessageConstants.ERROR_GENERATING_QR, message);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new TransactionException(ExceptionMessageConstants.ERROR_GENERATING_QR + response.getBody());
        }
    }

    @Override
    public PayWithVirtualAccountResponse payWithVirtualAccount(PayWitVirtualAccountRequest virtualAccountRequest) throws TransactionException, PayWithVirtualAccountException {
        final String PAY_WITH_VIRTUAL_ACCOUNT_URL = payWithVirtualAccountUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<PayWitVirtualAccountRequest> entity = new HttpEntity<>(virtualAccountRequest, headers);

        ResponseEntity<PayWithVirtualAccountResponse> response;
        try {
            response = restTemplate.postForEntity(
                    PAY_WITH_VIRTUAL_ACCOUNT_URL,
                    entity,
                    PayWithVirtualAccountResponse.class
            );
        } catch (Exception message) {
            throw new PayWithVirtualAccountException(ExceptionMessageConstants.ERROR_PAYING_WITH_VIRTUAL_ACCOUNT, message);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new TransactionException(ExceptionMessageConstants.ERROR_PAYING_WITH_VIRTUAL_ACCOUNT + response.getBody());
        }
    }

    @Override
    public GetWalletCardsResponse getWalletCards(GetWalletCardsRequest walletCardsRequest) throws GetWalletCardException, WalletCardException {
        String GET_WALLET_CARDS_URL =getWalletCardsUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authToken);

        HttpEntity<GetWalletCardsRequest> entity = new HttpEntity<>(walletCardsRequest, headers);

        ResponseEntity<GetWalletCardsResponse> response;
        try {
            response = restTemplate.postForEntity(
                    GET_WALLET_CARDS_URL,
                    entity,
                    GetWalletCardsResponse.class
            );
        } catch (Exception e) {
            throw new GetWalletCardException(ExceptionMessageConstants.ERROR_GETTING_WALLET_CARDS, HttpStatus.UNAUTHORIZED);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new WalletCardException(ExceptionMessageConstants.ERROR_GETTING_WALLET_CARDS + response.getBody());
        }
    }

    @Override
    public GenerateAlternativePaymentOptionResponse getAlternativePaymentOptions() throws GenerateAlternativePaymentOptionException {

        String ALTERNATIVE_PAYMENT_OPTION_URL = alternativePaymentOptionUrl;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", authToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<GenerateAlternativePaymentOptionResponse> response = restTemplate.exchange(
                    ALTERNATIVE_PAYMENT_OPTION_URL,
                    HttpMethod.GET,
                    entity,
                    GenerateAlternativePaymentOptionResponse.class
            );

            return response.getBody();
        } catch (HttpClientErrorException message) {
            throw new GenerateAlternativePaymentOptionException(ExceptionMessageConstants.FAILED_TO_FETCH_ALTERNATIVE_PAYMENT_OPTIONS, message);
        }
    }

    @Override
    public List<UssdBank> getUssdBanks() throws Exception {
        String USSD_BANK_URL = ussdBanksUrl;

        try {
            ResponseEntity<UssdBank[]> response = restTemplate.exchange(
                    USSD_BANK_URL,
                    HttpMethod.GET,
                    null,
                    UssdBank[].class
            );
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException e) {
            throw new Exception("Failed to fetch USSD banks", e);
        }
    }
}
