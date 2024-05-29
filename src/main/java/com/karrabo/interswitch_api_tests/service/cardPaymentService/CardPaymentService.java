package com.karrabo.interswitch_api_tests.service.cardPaymentService;

import com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests.*;
import com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses.*;
import com.karrabo.interswitch_api_tests.exception.*;
import com.karrabo.interswitch_api_tests.models.UssdBank;

import java.util.List;

public interface CardPaymentService {

    String authenticateOtp(AuthenticateOtpRequest authenticateOtpRequest);

    TokenizeCardResponse TokenizeCard_Recurrent(TokenizeCardRequest tokenizeCardRequest);

    PurchaseRecurrentResponse purchase_Recurrent (PurchaseRecurrentRequest purchaseRecurrentRequest) throws RecurrentPurchaseException;

    ConfirmDynamicTransferResponse confirmDynamicTransfer(String merchantCode, String transactionReference) throws TransactionException, ConfirmDynamicTransactionException;

    GetTransactionsResponse getTransactions(GetTransactionRequests getTransactionRequests) throws TransactionException, TransactionNotFoundException;

    GetRefundsResponse getRefunds(GetRefundsTransactionsRequests getRefundsTransactions) throws TransactionException, GetRefundTransactionException;

    GetRefundInfoResponse getRefundInfo(String refundReference) throws TransactionException, RefundInfoNotFoundException;

    CreateRefundTransactionResponse createRefundTransaction(CreateRefundTransactionRequest refundCreateRequest) throws TransactionException, CreateTransactionException;

    UssdPaymentResponse generateUssdPayment(UssdPaymentRequest ussdPaymentRequest) throws TransactionException, USSDPaymentException;

    QrPaymentResponse generateQrPayment(QrPaymentRequest qrPaymentRequest) throws TransactionException, QrPaymentException;

    PayWithVirtualAccountResponse payWithVirtualAccount(PayWitVirtualAccountRequest virtualAccountRequest) throws TransactionException, PayWithVirtualAccountException;

    GetWalletCardsResponse getWalletCards(GetWalletCardsRequest walletCardsRequest) throws GetWalletCardException, WalletCardException;

    GenerateAlternativePaymentOptionResponse getAlternativePaymentOptions() throws GenerateAlternativePaymentOptionException;

    List<UssdBank> getUssdBanks() throws Exception;



}
