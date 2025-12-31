package com.rushika.billdesk.Service;

import com.rushika.billdesk.DTO.InvoiceRequestDTO;
import com.rushika.billdesk.DTO.InvoiceResponseDTO;
import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Customer;
import com.rushika.billdesk.entity.Invoice;

import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO createInvoice(Long customerId, InvoiceRequestDTO dto);
    InvoiceResponseDTO getInvoiceById(Long id);
    List<InvoiceResponseDTO> getInvoicesByCustomer(Long customerId);
    void deleteInvoice(Long id);
}
