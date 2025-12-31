package com.rushika.billdesk.Repository;


import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Customer;
import com.rushika.billdesk.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCustomer(Customer customer);

    List<Invoice> findByCreatedBy(Admin admin);

    List<Invoice> findByInvoiceDateBetween(LocalDateTime start, LocalDateTime end);

    List<Invoice> findByCustomerAndInvoiceDateBetween(Customer customer, LocalDateTime start, LocalDateTime end);

    List<Invoice> findByCustomerId(Long customerId);

    List<Invoice> findByCreatedByAndInvoiceDateBetween(Admin createdBy, LocalDateTime start, LocalDateTime end);

}
