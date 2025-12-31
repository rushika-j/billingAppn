package com.rushika.billdesk.Service;

import com.rushika.billdesk.DTO.PaymentRequestDTO;
import com.rushika.billdesk.DTO.PaymentResponseDTO;
import com.rushika.billdesk.Mappers.PaymentMapper;
import com.rushika.billdesk.Repository.InvoiceRepository;
import com.rushika.billdesk.Repository.PaymentRepository;
import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Invoice;
import com.rushika.billdesk.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired private  PaymentRepository paymentRepository;
    @Autowired private  InvoiceRepository invoiceRepository;

    @Override
    public PaymentResponseDTO makePayment(PaymentRequestDTO dto) {
        Invoice invoice = invoiceRepository.findById(dto.getInvoiceId())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        Payment payment = new Payment(); // don't use the mapper here since DTO has no amount
        payment.setInvoice(invoice);
        payment.setAmount(invoice.getTotalAmount()); // set amount from invoice
        payment.setMethod(dto.getMethod());
        payment.setPaymentDate(LocalDateTime.now());

        Payment saved = paymentRepository.save(payment);

        // Optional: update invoice status
        invoice.setStatus("PAID");
        invoiceRepository.save(invoice);

        return PaymentMapper.toDTO(saved);
    }


    @Override
    public List<PaymentResponseDTO> getPaymentsByInvoice(Long invoiceId) {
        return paymentRepository.findByInvoiceId(invoiceId)
                .stream()
                .map(PaymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(PaymentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
    @Override
    public List<PaymentResponseDTO> getPaymentsByCustomer(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId).stream()
                .flatMap(invoice -> paymentRepository.findByInvoiceId(invoice.getId()).stream())
                .map(PaymentMapper::toDTO)
                .collect(Collectors.toList());
    }



}
