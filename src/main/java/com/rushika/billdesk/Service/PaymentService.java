package com.rushika.billdesk.Service;


import com.rushika.billdesk.DTO.PaymentRequestDTO;
import com.rushika.billdesk.DTO.PaymentResponseDTO;
import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Invoice;
import com.rushika.billdesk.entity.Payment;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    PaymentResponseDTO makePayment(PaymentRequestDTO dto);
    List<PaymentResponseDTO> getPaymentsByInvoice(Long invoiceId);
    PaymentResponseDTO getPaymentById(Long id);
    List<PaymentResponseDTO> getPaymentsByCustomer(Long customerId);

}
