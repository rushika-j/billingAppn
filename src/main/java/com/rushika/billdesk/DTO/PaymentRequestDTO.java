package com.rushika.billdesk.DTO;



public class PaymentRequestDTO {
    private Long invoiceId;
    private Double amount;
    private String method;

    public PaymentRequestDTO() {}

    public PaymentRequestDTO(Long invoiceId, Double amount, String method) {
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.method = method;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
