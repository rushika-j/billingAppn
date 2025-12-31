package com.rushika.billdesk.DTO;



import java.time.LocalDateTime;
import java.util.List;

public class InvoiceResponseDTO {
    private Long id;
    private String status;
    private LocalDateTime invoiceDate;
    private Double totalAmount;
    private Long customerId;
    private List<InvoiceItemDTO> items;

    public InvoiceResponseDTO() {}

    public InvoiceResponseDTO(Long id, String status, LocalDateTime invoiceDate, Double totalAmount, Long customerId, List<InvoiceItemDTO> items) {
        this.id = id;
        this.status = status;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<InvoiceItemDTO> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemDTO> items) {
        this.items = items;
    }
}
