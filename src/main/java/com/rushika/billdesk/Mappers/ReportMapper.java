package com.rushika.billdesk.Mappers;


import com.rushika.billdesk.DTO.ReportResponseDTO;
import com.rushika.billdesk.entity.Report;

public class ReportMapper {

    public static ReportResponseDTO toDTO(Report report) {
        ReportResponseDTO dto = new ReportResponseDTO();
        dto.setId(report.getId());
        dto.setType(report.getType());
        dto.setPeriodStart(report.getPeriodStart().toString());
        dto.setPeriodEnd(report.getPeriodEnd().toString());
        dto.setTotalSales(report.getTotalSales());
        dto.setTotalInvoices(report.getTotalInvoices());
        dto.setGeneratedAt(report.getGeneratedAt());
        return dto;
    }
}
