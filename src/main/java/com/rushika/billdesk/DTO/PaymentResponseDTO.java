package com.rushika.billdesk.DTO;



import java.time.LocalDateTime;

public class PaymentResponseDTO {
    private Long id;
    private Long invoiceId;
    private Double amount;
    private String method;
    private LocalDateTime paymentDate;
    private String razorpayOrderId;


    public PaymentResponseDTO() {}

    public PaymentResponseDTO(Long id, Long invoiceId, Double amount, String method, LocalDateTime paymentDate,String razorpayOrderId) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.method = method;
        this.paymentDate = paymentDate;
        this.razorpayOrderId=razorpayOrderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getRazorpayOrderId() { return razorpayOrderId; }
    public void setRazorpayOrderId(String razorpayOrderId) { this.razorpayOrderId = razorpayOrderId; }

}

