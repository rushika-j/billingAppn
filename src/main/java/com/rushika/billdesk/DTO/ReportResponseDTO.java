package com.rushika.billdesk.DTO;


import java.time.LocalDateTime;

public class ReportResponseDTO {
    private Long id;
    private String type;
    private String periodStart;
    private String periodEnd;
    private Double totalSales;
    private Integer totalInvoices;
    private LocalDateTime generatedAt;

    public ReportResponseDTO() {}

    public ReportResponseDTO(Long id, String type, String periodStart, String periodEnd, Double totalSales, Integer totalInvoices, LocalDateTime generatedAt) {
        this.id = id;
        this.type = type;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.totalSales = totalSales;
        this.totalInvoices = totalInvoices;
        this.generatedAt = generatedAt;
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
}
