package com.karrabo.interswitch_api_tests.service.implementations.cardPaymentServiceImpl;

import com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests.*;
import com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses.*;
import com.karrabo.interswitch_api_tests.exception.*;
import com.karrabo.interswitch_api_tests.service.authService.AuthenticationService;
import com.karrabo.interswitch_api_tests.service.cardPaymentService.CardPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardPaymentServiceImplTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CardPaymentService cardPaymentService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${interswitch.transactionRef}")
    private String transactionRef;

    @Value("${interswitch.authData}")
    private String authData;

    @Test
    public void testAuthenticateOtp_Success() {

            String accessToken = authenticationService.authenticate();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String requestBody = "{\"paymentId\": \"3530063\", \"otp\": \"123456\"}";
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange("https://qa.interswitchng.com/api/v3/purchases/otps/auths", HttpMethod.POST, entity, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());

        }

    @Test
    public void testTokenizeCard_Recurrent_Success() {
        TokenizeCardRequest request = new TokenizeCardRequest(
                transactionRef,
                authData);
        TokenizeCardResponse response = cardPaymentService.TokenizeCard_Recurrent(request);
        assertNotNull(response);
        System.out.println(response);
    }

    @Test
    public void purchase_Recurrent_Success() throws RecurrentPurchaseException {
        PurchaseRecurrentRequest request = PurchaseRecurrentRequest.builder()
                .customerId("johndoe@gmail.com")
                .amount("5000")
                .currency("NGN")
                .token("512341105007817082")
                .tokenExpiryDate("5003")
                .transactionRef("x14NPCcOnfkOipa")
                .build();

        PurchaseRecurrentResponse response = cardPaymentService.purchase_Recurrent(request);
        assertNotNull(response);
        System.out.println(response);
    }


    @Test
    public void testConfirmDynamicTransfer_Success() throws TransactionException, ConfirmDynamicTransactionException {
        String merchantCode = "MX6072";
        String transactionReference = "ndHHBZ7PpFNIc2W";

        ConfirmDynamicTransferResponse actualResponse = cardPaymentService.confirmDynamicTransfer(merchantCode, transactionReference);
        assertNotNull(actualResponse);
    }


    @Test
    public void testGetTransactions_WithTransactionReference() throws TransactionException, TransactionNotFoundException {

        GetTransactionRequests request = GetTransactionRequests.builder()
                .merchantCode("MX6072")
                .transactionReference("x14NPCcOnfkOipa")
                .startDate("11/01/2018")
                .endDate("05/29/2024")
                .pageSize(1)
                .pageNum(32)
                .build();
        GetTransactionsResponse response = cardPaymentService.getTransactions(request);
        assertNotNull(response);
        System.out.println(response);
        assertEquals(2, response.getCount());
    }



    @Test
    public void testGetTransactions_WithoutTransactionReference() throws TransactionException, TransactionNotFoundException {

        GetTransactionRequests request = GetTransactionRequests.builder()
                .merchantCode("MX6072")
                .startDate("11/01/2018")
                .endDate("05/29/2024")
                .pageNum(1)
                .pageSize(32)
                .build();
        GetTransactionsResponse response = cardPaymentService.getTransactions(request);
        assertNotNull(response);
        System.out.println(response);
    }


    @Test
    public void testGetRefundTransaction_success() throws TransactionException, GetRefundTransactionException {

        GetRefundsTransactionsRequests request = GetRefundsTransactionsRequests.builder()
                .merchantCode("MX6072")
                .startDate("11/01/2018")
                .endDate("12/03/2024")
                .pageNum(1)
                .pageSize(32)
                .build();
        GetRefundsResponse response = cardPaymentService.getRefunds(request);
        assertNotNull(response);
        System.out.println(response);
    }


    @Test
    public void testGetRefundInfo_success() throws TransactionException, RefundInfoNotFoundException {
        String transactionReference = "MX6072kjsd313sjdsyegdie9322ssaud0004";
        GetRefundInfoResponse response = cardPaymentService.getRefundInfo(transactionReference);
        assertNotNull(response);
        assertEquals(response.getMerchantCustomerId(), "johndoe@gmail.com");
    }


    @Test
    public void testCreateRefundTransaction_Success() throws CreateTransactionException, TransactionException {
        CreateRefundTransactionRequest request = CreateRefundTransactionRequest.builder()
                .refundReference("MX6072kdie9322ssaud0004jsd313sjdsyeg1723")
                .parentTransactionReference("QcYm7bz1N0ckTSb")
                .refundAmount("10")
                .build();

        CreateRefundTransactionResponse response = cardPaymentService.createRefundTransaction(request);
        assertNotNull(response);
        System.out.println(response);
        assertEquals("PARTIAL", response.getRefundType());
    }


    @Test
    public void testCreateRefundTransaction_Failure() {

        CreateRefundTransactionRequest request = CreateRefundTransactionRequest.builder()
                .refundReference("INVALID-REFUND-REFERENCE")
                .parentTransactionReference("INVALID-REFERENCE")
                .refundAmount("10")
                .build();

        assertThrows(CreateTransactionException.class, () -> {
            cardPaymentService.createRefundTransaction(request);
        });
    }


    @Test
    public void testGenerateUssdPayment_Success() throws TransactionException, USSDPaymentException {
        UssdPaymentRequest request = new UssdPaymentRequest();
        request.setAmount("1000");
        request.setBankCode("058");
        request.setSurcharge("0");
        request.setCurrencyCode("566");
        request.setMerchantTransactionReference("DhbRpGE1KpHmLPK");

        UssdPaymentResponse response = cardPaymentService.generateUssdPayment(request);
        assertNotNull(response);
        System.out.println(response);
        assertEquals(response.getTransactionReference(), response.getTransactionReference());
    }


    @Test
    public void testGenerateUssdPayment_Failure() {
        UssdPaymentRequest request = new UssdPaymentRequest();
        request.setAmount("invalid_amount");
        request.setBankCode("058");
        request.setSurcharge("0");
        request.setCurrencyCode("566");
        request.setMerchantTransactionReference("invalid_reference");

        assertThrows(USSDPaymentException.class, () -> {
            cardPaymentService.generateUssdPayment(request);
        });
    }



    @Test
    public void testGenerateQrPayment_Success() throws QrPaymentException, TransactionException {
        QrPaymentRequest request =  QrPaymentRequest.builder()
                .amount("1000")
                .surcharge("0")
                .currencyCode("566")
                .merchantTransactionReference("DhbRpGE1KpHmLPK")
                .build();

        QrPaymentResponse response = cardPaymentService.generateQrPayment(request);
        assertNotNull(response);
    }


    @Test
    public void testGenerateQrPayment_Failure() {

        QrPaymentRequest request =  QrPaymentRequest.builder()
                .amount("invalid_amount")
                .surcharge("no_charge")
                .currencyCode("566")
                .merchantTransactionReference("invalid_reference")
                .build();
        assertThrows(QrPaymentException.class, () -> {
            cardPaymentService.generateQrPayment(request);
        });
    }



    @Test
    public void testPayWithVirtualAccount_Success() throws TransactionException, PayWithVirtualAccountException {
        PayWitVirtualAccountRequest request = new PayWitVirtualAccountRequest();
        request.setMerchantCode("MX6072");
        request.setPayableCode("9405967");
        request.setCurrencyCode("566");
        request.setAmount("100000");
        request.setAccountName("QTB Test Account");
        request.setTransactionReference("SeL0U12z16TswB6");

        PayWithVirtualAccountResponse response = cardPaymentService.payWithVirtualAccount(request);
        assertNotNull(response);
        System.out.println(response);
    }


    @Test
    public void testPayWithVirtualAccount_Failure() {
        PayWitVirtualAccountRequest request = new PayWitVirtualAccountRequest();
        request.setMerchantCode("InvalidCode");
        request.setPayableCode("InvalidCode");
        request.setCurrencyCode("InvalidCode");
        request.setAmount("invalid_amount");
        request.setAccountName("Invalid Account");
        request.setTransactionReference("InvalidReference");

        assertThrows(PayWithVirtualAccountException.class, () -> {
            cardPaymentService.payWithVirtualAccount(request);
        });
    }
}