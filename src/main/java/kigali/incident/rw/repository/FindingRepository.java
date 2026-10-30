package kigali.incident.rw.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import kigali.incident.rw.domain.Finding;

public interface FindingRepository extends JpaRepository<Finding, UUID> {

    @Query("SELECT f FROM Finding f WHERE f.incidentReport.resident.nationalId = :nationalId ORDER BY f.recordedDate DESC")
    List<Finding> findFindingsOfResident(@Param("nationalId") String nationalId);

    boolean existsByIncidentReportId(UUID incidentReportId);
}
