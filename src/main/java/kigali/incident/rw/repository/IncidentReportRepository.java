package kigali.incident.rw.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kigali.incident.rw.domain.IncidentReport;
import kigali.incident.rw.domain.ReportStatus;

@Repository
public interface IncidentReportRepository extends JpaRepository<IncidentReport, UUID> {

    List<IncidentReport> findByStatusOrderByIncidentDateAsc(ReportStatus status);

    List<IncidentReport> findByIncidentDateBetweenOrderByIncidentDateAsc(LocalDate start, LocalDate end);

    boolean existsByResponseOfficerIdAndIncidentDateAndStatusNot(UUID responseOfficerId, LocalDate incidentDate, ReportStatus status);

    long countByIncidentDate(LocalDate incidentDate);

    long countByStatus(ReportStatus status);

    @Query("SELECT r.status, COUNT(r) FROM IncidentReport r GROUP BY r.status")
    List<Object[]> countIncidentReportsByStatus();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE IncidentReport r SET r.status = kigali.incident.rw.domain.ReportStatus.DISMISSED "
         + "WHERE r.responseOfficer.id = :responseOfficerId AND r.incidentDate = :date "
         + "AND r.status <> kigali.incident.rw.domain.ReportStatus.CLOSED")
    int cancelResponseOfficerDay(@Param("responseOfficerId") UUID responseOfficerId, @Param("date") LocalDate date);
}
