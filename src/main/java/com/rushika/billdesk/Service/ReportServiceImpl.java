package com.rushika.billdesk.Service;


import com.rushika.billdesk.DTO.ReportResponseDTO;
import com.rushika.billdesk.Mappers.ReportMapper;
import com.rushika.billdesk.Repository.InvoiceRepository;
import com.rushika.billdesk.Repository.ReportRepository;
import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Invoice;
import com.rushika.billdesk.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    @Autowired private  ReportRepository reportRepository;
    @Autowired private  InvoiceRepository invoiceRepository;
    @Override
    public Report generateReport(String type, String start, String end, Admin admin) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime periodStart, periodEnd;

        switch (type.toUpperCase()) {
            case "DAILY" -> {
                periodStart = now.toLocalDate().atStartOfDay();
                periodEnd = periodStart.plusDays(1);
            }
            case "MONTHLY" -> {
                periodStart = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
                periodEnd = periodStart.plusMonths(1);
            }
            case "ANNUAL" -> {
                periodStart = now.withDayOfYear(1).toLocalDate().atStartOfDay();
                periodEnd = periodStart.plusYears(1);
            }
            default -> throw new IllegalArgumentException("Invalid report type: " + type);
        }

        List<Invoice> invoices = invoiceRepository.findByInvoiceDateBetween(periodStart, periodEnd);

        double totalSales = invoices.stream().mapToDouble(Invoice::getTotalAmount).sum();
        int totalInvoices = invoices.size();

        Report report = new Report();
        report.setType(type.toUpperCase());
        report.setPeriodStart(periodStart.toLocalDate().toString());
        report.setPeriodEnd(periodEnd.toLocalDate().toString());
        report.setTotalSales(totalSales);
        report.setTotalInvoices(totalInvoices);
        report.setGeneratedAt(now);
        report.setGeneratedBy(admin);

        return reportRepository.save(report);  // ✅ Return the Report entity, matching the interface
    }
}
