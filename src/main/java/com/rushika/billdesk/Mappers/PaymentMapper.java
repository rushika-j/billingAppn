package com.rushika.billdesk.Mappers;


import com.rushika.billdesk.DTO.PaymentRequestDTO;
import com.rushika.billdesk.DTO.PaymentResponseDTO;
import com.rushika.billdesk.entity.Invoice;
import com.rushika.billdesk.entity.Payment;

public class PaymentMapper {

    public static Payment toEntity(PaymentRequestDTO dto) {
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setMethod(dto.getMethod());

        Invoice invoice = new Invoice();
        invoice.setId(dto.getInvoiceId());
        payment.setInvoice(invoice);

        return payment;
    }

    public static PaymentResponseDTO toDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setInvoiceId(payment.getInvoice().getId());
        dto.setAmount(payment.getAmount());
        dto.setMethod(payment.getMethod());
        dto.setPaymentDate(payment.getPaymentDate());
        return dto;
    }
}
