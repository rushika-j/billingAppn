package com.rushika.billdesk.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // DAILY, MONTHLY, ANNUAL
    private String periodStart; // e.g. "2025-07-01"
    private String periodEnd;   // e.g. "2025-07-31"
    private Double totalSales;
    private Integer totalInvoices;
    private LocalDateTime generatedAt;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin generatedBy;

    private String notes;

    public Report() {}

    public Report(String type, String periodStart, String periodEnd, Double totalSales, Integer totalInvoices, LocalDateTime generatedAt, Admin generatedBy, String notes) {
        this.type = type;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.totalSales = totalSales;
        this.totalInvoices = totalInvoices;
        this.generatedAt = generatedAt;
        this.generatedBy = generatedBy;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    public Integer getTotalInvoices() {
        return totalInvoices;
    }

    public void setTotalInvoices(Integer totalInvoices) {
        this.totalInvoices = totalInvoices;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public Admin getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(Admin generatedBy) {
        this.generatedBy = generatedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

