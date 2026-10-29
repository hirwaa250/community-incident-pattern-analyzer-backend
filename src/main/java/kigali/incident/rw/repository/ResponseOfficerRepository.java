package kigali.incident.rw.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kigali.incident.rw.domain.ResponseOfficer;

@Repository
public interface ResponseOfficerRepository extends JpaRepository<ResponseOfficer, UUID> {

    @Query("SELECT o FROM ResponseOfficer o JOIN o.incidentTypes t WHERE LOWER(t.name) = LOWER(:name)")
    List<ResponseOfficer> findResponseOfficersByIncidentTypeName(@Param("name") String name);

    boolean existsByCellCellNumberAndIdNot(int cellNumber, UUID id);
}
