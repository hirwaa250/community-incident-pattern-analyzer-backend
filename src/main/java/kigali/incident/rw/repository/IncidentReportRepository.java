package kigali.incident.rw.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.incident.rw.domain.IncidentReport;
import kigali.incident.rw.domain.ReportStatus;

@Repository
public interface IncidentReportRepository extends JpaRepository<IncidentReport, UUID> {

    List<IncidentReport> findByStatusOrderByIncidentDateAsc(ReportStatus status);

    List<IncidentReport> findByIncidentDateBetweenOrderByIncidentDateAsc(LocalDate start, LocalDate end);
}
