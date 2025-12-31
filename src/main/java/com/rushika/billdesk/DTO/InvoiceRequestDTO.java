package com.rushika.billdesk.DTO;


import java.util.List;

public class InvoiceRequestDTO {
    private Long customerId;
    private List<InvoiceItemDTO> items;
    private Double totalAmount;
    private String status;

    public InvoiceRequestDTO() {}

    public InvoiceRequestDTO(Long customerId, List<InvoiceItemDTO> items, Double totalAmount, String status) {
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = status;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

