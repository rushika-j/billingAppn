package com.rushika.billdesk.Service;

import com.rushika.billdesk.DTO.InvoiceRequestDTO;
import com.rushika.billdesk.DTO.InvoiceResponseDTO;
import com.rushika.billdesk.Mappers.InvoiceMapper;
import com.rushika.billdesk.Repository.CustomerRepository;
import com.rushika.billdesk.Repository.InvoiceRepository;
import com.rushika.billdesk.Repository.ProductRepository;
import com.rushika.billdesk.entity.Customer;
import com.rushika.billdesk.entity.Invoice;
import com.rushika.billdesk.entity.InvoiceItem;
import com.rushika.billdesk.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

   @Autowired private  InvoiceRepository invoiceRepository;
  @Autowired  private  CustomerRepository customerRepository;
   @Autowired private  ProductRepository productRepository;

    @Override
    public InvoiceResponseDTO createInvoice(Long customerId, InvoiceRequestDTO dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Invoice invoice = InvoiceMapper.toEntity(dto);
        invoice.setCustomer(customer);

        for (InvoiceItem item : invoice.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            int requestedQty = item.getQuantity();
            int availableQty = product.getStockQuantity();

            if (requestedQty > availableQty) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            // Reduce stock
            product.setStockQuantity(availableQty - requestedQty);

            // Calculate price with tax
            double unitPrice = product.getPrice();
            double taxRate = product.getTaxPercentage(); // e.g., 18.0 for 18%
            double priceWithTax = unitPrice * (1 + taxRate / 100.0);
            double totalPrice = priceWithTax * requestedQty;

            item.setProduct(product);
            item.setUnitPrice(unitPrice);     // base price
            item.setTotalPrice(totalPrice);   // total with tax
        }

        double total = invoice.getItems().stream()
                .mapToDouble(InvoiceItem::getTotalPrice)
                .sum();

        invoice.setTotalAmount(total);
        invoice.setInvoiceDate(LocalDateTime.now());

        Invoice saved = invoiceRepository.save(invoice);

        // Save updated stock for all products
        invoice.getItems().forEach(item -> productRepository.save(item.getProduct()));

        return InvoiceMapper.toDTO(saved);
    }

    @Override
    public InvoiceResponseDTO getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .map(InvoiceMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Override
    public List<InvoiceResponseDTO> getInvoicesByCustomer(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId)
                .stream()
                .map(InvoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
