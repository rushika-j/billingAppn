package com.rushika.billdesk.Service;

import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Report;

import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Report;

public interface ReportService {
    Report generateReport(String type, String start, String end, Admin admin);
}
