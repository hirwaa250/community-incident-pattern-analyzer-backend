package kigali.incident.rw.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kigali.incident.rw.domain.IncidentType;

@Repository
public interface IncidentTypeRepository extends JpaRepository<IncidentType, UUID> {

    @Query("SELECT t FROM IncidentType t WHERE t.responseOfficers IS EMPTY")
    List<IncidentType> findUnusedIncidentTypes();
}
