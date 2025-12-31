package com.rushika.billdesk.Repository;

import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Invoice;
import com.rushika.billdesk.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByInvoice(Invoice invoice);

    List<Payment> findByProcessedBy(Admin admin);

    List<Payment> findByPaymentDateBetween(LocalDateTime start, LocalDateTime end);

    List<Payment> findByInvoiceId(Long invoiceId);

    List<Payment> findByInvoiceCustomerId(Long customerId);
}