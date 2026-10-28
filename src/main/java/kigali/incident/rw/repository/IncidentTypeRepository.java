package kigali.incident.rw.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.incident.rw.domain.IncidentType;

@Repository
public interface IncidentTypeRepository extends JpaRepository<IncidentType, UUID> {

}
