package com.rushika.billdesk.Repository;

import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByGeneratedBy(Admin admin);

    List<Report> findByGeneratedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Report> findByType(String type);
}