package com.rushika.billdesk.entity;



import jakarta.persistence.*;
import java.time.LocalDateTime;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime paymentDate;
    private Double amount;
    private String method;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin processedBy;

    public Payment() {}

    public Payment(LocalDateTime paymentDate, Double amount, String method, Invoice invoice, Admin processedBy) {
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.method = method;
        this.invoice = invoice;
        this.processedBy = processedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Admin getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(Admin processedBy) {
        this.processedBy = processedBy;
    }
}
